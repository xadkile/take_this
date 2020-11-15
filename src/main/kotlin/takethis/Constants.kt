package takethis

object Constants {
    val ICON_RESOURCE_PATH= "images/icon_neon.png"
    val ICON_URL=MyApp::class.java.classLoader.getResource(Constants.ICON_RESOURCE_PATH)
    val OPEN_INDEPENDENT_WINDOW_REQUEST = "System does not support tray. Open float window instead? No-to exit the application"
    val FILE_CHOOSER_WINDOW_TITLE = "Save screenshot"
    val DEFAULT_IMAGE_FILE_TYPE = "png"
}