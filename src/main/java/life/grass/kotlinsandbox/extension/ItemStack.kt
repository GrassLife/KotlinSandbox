package life.grass.kotlinsandbox.extension

import org.bukkit.inventory.ItemStack

var ItemStack.displayName: String?
    get() {
        return itemMeta?.displayName
    }
    set(value) {
        itemMeta?.setDisplayName(value)
    }