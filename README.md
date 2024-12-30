# InfinityExpansion 2

The kotlin rewrite version of [InfinityExpansion](https://github.com/Mooy1/InfinityExpansion) by Mooy1, with additional
features and improvements.

> [!IMPORTANT]
> InfinityExpansion2 is **NOT** compatible with InfinityExpansion, you cannot replace InfinityExpansion with
> InfinityExpansion2 in an existing server.  
> It is recommended to install InfinityExpansion2 in a new server.

## Download

(WIP, check Slimefun discord for preview builds)

### Requirement

- Java 21 or higher
- Minecraft 1.20 or higher
- Paper or its forks
- (Optional) SlimefunTranslation

## Configuration

`/plugins/InfinityExpansion2/config.yml` is the main configuration file for InfinityExpansion2.

Some of the config options are in `/plugins/Slimefun/Items.yml` under the specific item's settings.

### Terms

Before you start editing the config, you will want to know some terms and the information associated with them:

- **Tick rate**: The amount of Slimefun ticks a machine runs. All the machines have a default tick rate of 1. (Unit:
  Slimefun ticks, Range: 1 ~ 3600)
- **Energy per tick / per use**: This is very easy to understand, it is the amount of energy consumed per tick or per
  use.
- **Output interval**: The amount of machine runs required to produce output. (Range: 1 ~ 3600)

For example, if a machine has a tick rate of 10 and an output interval of 20, the machine runs and consumes energy every 5 seconds (10 ticks), and produces output every 100 seconds (200 ticks).

## Credits

- Mooy1: Original author of InfinityExpansion.
- JustAHuman & Idra: The config structure of Quarries.
- Anyone else who provided feedback and suggestions during development.
