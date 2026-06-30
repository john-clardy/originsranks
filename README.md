# Origins Ranks

Origins Ranks is a server-side addon for FTB Ranks that adds a custom rank condition for the Origins mod, allowing ranks to be automatically applied based on a player's selected Origin.

## Requirements

* Minecraft 1.21.1
* NeoForge
* Architectuary API
* FTB Ranks
* FTB Library
* Origins (IAFEnvoy NeoForge)

## Condition

**Type:** `originranks:has_origin`

### Properties

| Property | Required | Description                                              |
| -------- | -------- | -------------------------------------------------------- |
| `origin` | Yes      | The Origin ID to check (for example `origins:avian`).    |
| `layer`  | No       | The Origin layer to check. Defaults to `origins:origin`. |

## Example

```snbt
avian: {
    name: "Avian"
    power: 50

    condition: {
        type: "originranks:has_origin"
        origin: "origins:avian"
    }
}
```

Specifying a layer:

```snbt
condition: {
    type: "originranks:has_origin"
    origin: "origins:archer"
    layer: "origins:class"
}
```

## How It Works

Whenever FTB Ranks evaluates a player's ranks, the `has_origin` condition checks the player's current Origin from the Origins mod. If the configured Origin matches, the rank is considered active.

No commands, scoreboards, datapacks, or KubeJS scripts are required.

## Notes

* This addon is server-side only.
* The default Origin layer is `origins:origin`, so `layer` can usually be omitted.
* The `origin` value must match the Origin's full resource ID.
