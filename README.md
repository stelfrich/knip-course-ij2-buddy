knip-course-ij2-buddy
=====================

A KNIME Image Processing plugin that wraps an ImageJ2 *Command*.

If you are using this plugin as skeleton for a new project, you have to change the `EclipseHelper` according to [Point 4](https://github.com/knime-ip/knip-imagej2#add-your-own-imagej2-plugins-to-knime).

Exercise
--------

1. Create a *lib/* folder and download the required artifacts to build the Coloc2 wrapper from [https://maven.imagej.net](https://maven.imagej.net) into that folder. 
2. Change the plugins Build configuration to include the *lib/* folder
3. Add the libraries to the Runtime classpath
4. Check if your *Command* is picked up when you execute the knime-launch-configuration
5. Export the plugin to your local KNIME installation (Right click *knip-course-ij2-buddy* > Export > Plug-in Development > Deployable plug-ins and fragments; select the plugins folder of your installation as destination)
6. Restart KNIME to see if it is picked up
