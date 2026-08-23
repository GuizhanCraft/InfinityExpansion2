# AGENTS.md

## Project

InfinityExpansion2 — Kotlin rewrite of the Slimefun addon InfinityExpansion. Paper plugin (MC 1.20+, Java 17), single Gradle module (Kotlin DSL, wrapper 8.14.3, Kotlin 2.2.21). There are **no tests**; verification is build + manual in-game testing.

## Commands

- Build: `./gradlew clean shadowJar` → shaded jar in `build/libs/` (this is exactly what CI runs).
- Dev server: `./gradlew runServer` (run-paper). Server lives in `run/` (gitignored, already populated), runs MC 1.20.6 and auto-downloads Slimefun, SlimeHUD, GuizhanCraft.
- Do not add a test/lint pipeline unless asked — none exists.

## Build system quirks

- **Version is generated**: `preview-<yyMMddHHmm UTC timestamp>` in `build.gradle.kts`. There are no release tags; CI/blob-build workflows build from `master`.
- **plugin.yml is generated** by the plugin-yml plugin from the `bukkit {}` block in `build.gradle.kts`. Edit name/commands/permissions/depend there — there is no `plugin.yml` source file.
- **Kotlin stdlib/reflect are `compileOnly` on purpose** — they are downloaded at runtime by `BukkitLibraryManager` (libby) in `InfinityExpansion2.load()`, from `-DcentralRepository=<url>` JVM prop or a Google Maven mirror default. Do not make them `implementation`.
- **Shadow relocations**: all shaded libs are relocated to `net.guizhanss.infinityexpansion2.libs.*` with `minimize()` enabled. When adding an `implementation` dependency, add a matching `doRelocate(...)` entry in `tasks.shadowJar` or it will ship unrelocated.
- Slimefun is compiled against `com.github.slimefun:Slimefun4:experimental-SNAPSHOT` (JitPack, **experimental** branch, not master). README states only official Slimefun and the Gugu fork are supported at runtime.

## Layout (`src/main/kotlin/net/guizhanss/infinityexpansion2/`)

- `InfinityExpansion2.kt` — main class, extends GuizhanLib `AbstractAddon`; `enable()` shows the boot order (config → tags → localization → groups → items → researches → integrations → commands → listeners/tasks → metrics). Services are `lateinit` companion vars (`configService`, `localization`, …).
- `api/` — public API for other plugins (`InfinityExpansion2API`, `api/mobsim`).
- `core/` — infrastructure: services, config, commands, recipes, menu, items attributes/handlers.
- `implementation/` — actual content: items, guide groups, listeners, tasks, `setup/` (research & mob-simulation registration).
- `integration/` — optional-plugin hooks (SlimefunTranslation, SlimeHUD, …).
- `utils/` — helpers incl. `bukkitext/`, `slimefunext/`, `tags/` (custom tags loaded from `resources/tags/*.json`).

## Conventions

- Localization: **only edit `src/main/resources/lang/en.yml`**. All other `lang/*.yml` are managed by Crowdin (`crowdin.yml`) and land as `chore(i18n)` commits/PRs — manual edits to them will be overwritten.
- Style per `.editorconfig`: 4-space indent everywhere except `*.yml` (2 spaces) and `*.json` (tabs); LF, UTF-8, final newline.
- Commits use conventional style, mostly single-line, e.g. `chore(i18n): update translations`, `feat: ...`.
- InfinityExpansion2 is **not compatible** with InfinityExpansion v1 — do not add migration/compat code unless explicitly requested (the conflict check in `enable()` is intentionally commented out).
