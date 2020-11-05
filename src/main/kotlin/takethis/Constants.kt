package takethis

object Constants {
    val ICON_RESOURCE_PATH= "images/Icon.png"
    val ICON_URL=MyApp::class.java.classLoader.getResource(Constants.ICON_RESOURCE_PATH)
    val OPEN_INDEPENDENT_WINDOW_REQUEST = "System does not support tray. Open float window instead? No-to exit the application"
}