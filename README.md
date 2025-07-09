
# Quest Peripheral

## About

Quest Peripheral adds a new Block (the aptly named "Quest Peripheral") that you can use to get FTB Quests quest data to use in your Lua scripts

## How to Use

Using the Quest Peripheral is pretty straightforward, as with all other ComputerCraft peripherals, you just connect it to a computer, directly adjacent or via wired modems.
You can call the functions as with any other peripheral, directly or by wrapping it.

## Functions

### `refreshQuestFile()`
This function is called automatically whenever a Computer connects to the Peripheral, but you can also run it yourself to refresh the data, if the Quests have changed since the last world load.

 **Returns:** `true`/`false` 

### `isQuestFileLoaded()`
This function simply checks if a reference to FTB Quest's so called "quest file" exists, in case it hasn't been loaded yet, like just after world loading.

**Returns:** `true`/`false`

### `getPlayer()`
This function retrieves the name of the player that's stored to the Peripheral, for quest progress data purposes

**Returns:** String 

### `getAllQuests()`
This function retrieves all Quest IDs and their titles, regardless of the chapter they are in

**Returns:** Table `{String questID : String questTitle}`
**Example:**   
```lua
{
  ["1B766784FBBE7D51"] = "Getting Started",
  ["5A8F81F26C0A1234"] = "Mine Iron",
  ...
}
```

### `getQuestLines()`
This function retrieves the Quest Chapter IDs and their titles

**Returns:** Table `{String chapterID : String chapterTitle}`
**Example:**
```lua
{
  ["1B766784FBBE7D51"] = "Chapter 01: Getting Started",
  ["5A8F81F26C0A1234"] = "Chapter 02: Teching Up",
  ...
}
```

### `setPlayer(String playername)`
This function is used to change which Player the Quest Peripheral is bound to, to retrieve that player's team Quest progress data

**Returns:** `true` when the player name was stored

**NOTE:** This function does not check that the given Player name actually belongs to an existing player with quest progress.

### `getChapterQuests(String chapterID)`
This Function retrieves the Quests within the Chapter with the given ID

**Returns:** Table `{String questID : String questName}`
**Example:**
```lua
{
  ["1B766784FBBE7D51"] = "Getting Started",
  ["5A8F81F26C0A1234"] = "Mine Iron",
  ...
}
```

### `getData(String id)` *NYI*
This is the base function for getting the basic Data of Quests and Chapters.
it retrieves the Data of the Object with the given ID.
See `getDataQuest()` and `getDataChapter()` for their respective outputs.

### `getDataQuest(String questID)` 
This function retrieves the Quest Data of the given Quest Object.

**Returns:** Table  `{String property : data}`
**Example:**
```lua
{
  id = "77F9E320987AB234",
  title = "Craft a Pickaxe",
  subtitle = "Start your mining journey",
  description = {
    "Use wooden planks and sticks to make a pickaxe.",
    "This is required for mining stone and ores."
  },
  canBeRepeated = false,
  optional = false,
  tasks = {
    ["1B766784FBBE7D51"] = {
      type = "Item",
      title = "Craft Wooden Pickaxe",
      icon = "minecraft:item/wooden_pickaxe",
      optional = "false"
    }
  },
  rewards = {
    ["5A8F81F26C0A1234"] = {
      type = "Item",
      title = "Iron Ingot",
      icon = "minecraft:item/iron_ingot"
    }
  },
  x = 1,
  y = 0
}
```

### `getDataChapter(String chapterID)` *NYI*
This function retrieves the Chapter Data of the given Chapter Object.

**Returns:**

### `getProgress(String id)` *NYI*
This is the base function to get Progress Data.
It retrieves the Progress Data of the connected Player's Team for the Object with the given ID.
See `getProgressQuest()` and `getProgressChapter()` for their respective Outputs.

**Returns:** 

### `getProgressQuest(String QuestID)` *NYI*
This function retrieves the connected Player's Team Progress Data for the Quest with the given ID.

**Returns:**

### `getProgressChapter(chapterID)` *NYI*
This function retrieves the connected Player's Team Progress Data for the Chapter with the given ID.

**Returns:**