package com.wretchedminer.originsranks;


import com.iafenvoy.origins.attachment.OriginDataHolder;
import com.iafenvoy.origins.util.HolderHelper;
import dev.ftb.mods.ftblibrary.snbt.SNBTCompoundTag;
import dev.ftb.mods.ftbranks.api.RankCondition;
import dev.ftb.mods.ftbranks.api.event.RankEvent;
import dev.ftb.mods.ftbranks.api.event.RegisterConditionsEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class HasOriginCondition implements RankCondition {
    private final ResourceLocation origin;
    private final ResourceLocation layer;

    public HasOriginCondition(SNBTCompoundTag tag) {
        String originString = tag.contains("origin")
                ? tag.getString("origin")
                : "origins:human";

        String layerString = tag.contains("layer")
                ? tag.getString("layer")
                : "origins:origin";

        this.origin = ResourceLocation.parse(originString);
        this.layer = ResourceLocation.parse(layerString);
    }

    public static void register() {
        RankEvent.REGISTER_CONDITIONS.register(HasOriginCondition::registerConditions);
    }

    private static void registerConditions(RegisterConditionsEvent event) {
        event.register("originranks:has_origin", (rank, tag) -> new HasOriginCondition(tag));
    }

    @Override
    public String getType() {
        return "originranks:has_origin";
    }

    @Override
    public boolean isRankActive(ServerPlayer player) {
        OriginDataHolder holder = OriginDataHolder.get(player);

        return holder.getOrigins().entrySet().stream().anyMatch(entry -> {
            ResourceLocation playerLayer = HolderHelper.id(entry.getKey());
            ResourceLocation playerOrigin = HolderHelper.id(entry.getValue());

            return playerLayer.equals(layer) && playerOrigin.equals(origin);
        });
    }

    @Override
    public void save(SNBTCompoundTag tag) {
        tag.putString("origin", origin.toString());
        tag.putString("layer", layer.toString());
    }
}