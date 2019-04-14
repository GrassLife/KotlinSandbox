package life.grass.kotlinsandbox

import life.grass.kotlinsandbox.house.HouseContainer
import life.grass.kotlinsandbox.listener.HousingListener
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author BlackBracken
 */
class KotlinSandbox : JavaPlugin() {

    override fun onEnable() {
        val houseContainer = HouseContainer()
        server.pluginManager.registerEvents(HousingListener(houseContainer), this)
    }

}