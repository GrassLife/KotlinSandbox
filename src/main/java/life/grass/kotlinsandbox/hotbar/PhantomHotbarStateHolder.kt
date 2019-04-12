package life.grass.kotlinsandbox.hotbar

import org.bukkit.entity.Player

/**
 * @author BlackBracken
 */
object PhantomHotbarStateHolder {

    val openingPhantomHotbarMap = mutableMapOf<Player, PhantomHotbar>()

}

fun Player.openPhantomHotbar(phantomHotbar: PhantomHotbar) {
    if (this.isOpeningHotbarSlot()) {
        return
    }

    PhantomHotbarStateHolder.openingPhantomHotbarMap[this] = phantomHotbar
    phantomHotbar.openBy(this)
}

fun Player.closePhantomHotbar() {
    PhantomHotbarStateHolder.openingPhantomHotbarMap[this]?.closeBy(this) ?: return
    PhantomHotbarStateHolder.openingPhantomHotbarMap.remove(this)
}

fun Player.getOpeningPhantomHotbar(): PhantomHotbar? = PhantomHotbarStateHolder.openingPhantomHotbarMap[this]

fun Player.isOpeningHotbarSlot(): Boolean = this.getOpeningPhantomHotbar() != null