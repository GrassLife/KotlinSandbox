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

fun Player.choosePhantomHotbarSlot(slot: Int) {
    val phantomHotbar = this.getOpeningPhantomHotbar() ?: return

    phantomHotbar.chooseBy(this, slot)
    closePhantomHotbar()
}

fun Player.closePhantomHotbar() {
    PhantomHotbarStateHolder.openingPhantomHotbarMap[this]?.closeBy(this) ?: return
    PhantomHotbarStateHolder.openingPhantomHotbarMap.remove(this)
}

fun Player.getOpeningPhantomHotbar(): PhantomHotbar? = PhantomHotbarStateHolder.openingPhantomHotbarMap[this]

fun Player.isOpeningHotbarSlot(): Boolean = this.getOpeningPhantomHotbar() != null