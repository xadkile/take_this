package takethis

import dorkbox.systemTray.Menu
import dorkbox.systemTray.MenuItem
import dorkbox.systemTray.SystemTray
import javafx.application.Platform
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tornadofx.launch
import java.awt.PopupMenu
import java.awt.TrayIcon
import java.io.IOException
import java.net.URL
import javax.imageio.ImageIO
import javax.swing.SwingUtilities

/**
 * TODO
 * add save-image functionality
 * add configure:
 *      transparent window always on top?
 *      test window always on top?
 *      -Djdk.gtk.version=3
 */
fun main(args: Array<String>) {
    println("start app")
    launch<MyApp>(args)
    println("end")

//    SwingUtilities.invokeLater {
//        Platform.setImplicitExit(false)
//        val iconUrl = URL("file:///home/abc/Documents/gits/take_this/src/main/resources/images/TacoCloud.png")
//        val image = ImageIO.read(iconUrl)
//        val icon = TrayIcon(image)
//        val popupMenu = PopupMenu()
//        val tray = java.awt.SystemTray.getSystemTray()
//        val exitItem = java.awt.MenuItem("Exit")
//        exitItem.addActionListener { event ->
//            println("Exit...")
//            Platform.exit()
//            tray.remove(icon)
//        }
//        val takePictureItem = java.awt.MenuItem("Take picture")
//        takePictureItem.addActionListener { event ->
//            Common.openTransparentWindow()
//        }
//        popupMenu.add(takePictureItem)
//        popupMenu.add(exitItem)
//
//        icon.popupMenu = popupMenu
//        tray.add(icon)
//    }
//    launch<MyApp>(args)
//    println("end")
}


//    Platform.setImplicitExit(false)
//    val systemTray: SystemTray? = SystemTray.get()
//    if (systemTray != null) {
//        Platform.setImplicitExit(false)
//        val iconUrl: URL = URL("file:///home/abc/Documents/gits/take_this/src/main/resources/images/TacoCloud.png")
//        systemTray.setImage(iconUrl)
//
//        val menu: Menu = systemTray.menu
//        val exitItem = MenuItem("Exit") { event ->
////                Platform.exit()
//            systemTray.shutdown()
//        }
//        val takePicItem = MenuItem("Take pic") { event ->
//            println("take pic....")
//            Common.openTransparentWindow()
//        }
//        val openTestWindow = MenuItem("Test window") { event ->
//            println("open test window...")
//        }
//        menu.add(takePicItem)
//        menu.add(openTestWindow)
//        menu.add(exitItem)
//    } else {
//        println("unable to use system tray, open window?")
//    }