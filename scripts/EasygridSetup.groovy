includeTargets << grailsScript("_GrailsInit")

target(easygridSetup: "The description of the script goes here!") {
    //todo - create EasygridConfig file
    File configFile = new File(basedir, 'grails-app/conf/EasygridConfig.groovy')
    if (!configFile.exists()) {
        configFile.createNewFile()
        configFile << """
            easygrid{

            }
            """.stripIndent()
    }

    // copy the templates
    copyTemplates(['jqGridRenderer', 'classicGridRenderer', 'dataTablesGridRenderer', 'visualizationGridRenderer', 'filterFormRenderer'])


}

setDefaultTarget(easygridSetup)

private copyTemplates(templates) {
    try {
        File dest = new File(basedir, 'grails-app/views/templates/easygrid')
        if (!dest.exists()) {
            dest.mkdir()
        }
        templates.each {
            ant.copy file: new File(easygridPluginDir, "grails-app/views/templates/easygrid/_${it}.gsp"), todir: dest, overwrite: false
        }
    } catch (Exception e) {
        e.printStackTrace()
    }
}
