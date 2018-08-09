import jenkins.model.*

Jenkins.instance.setNumExecutors(0)



YELLOW = '\u001B[33m';
RESET  = '\u001B[0m';

def configSimpleTheme(def descriptor, String cssUrl, String jsUrl) {
  if ( !(cssUrl == null || cssUrl.trim().isEmpty()) ) {
    println("${YELLOW}Setting up css style: ${cssUrl.trim()}${RESET}")
    descriptor.cssUrl = cssUrl.trim()
  }
  if ( !(jsUrl == null ||jsUrl.trim().isEmpty()) ) {
    println("${YELLOW}Setting up js: ${jsUrl.trim()}${RESET}")
    descriptor.jsUrl = jsUrl.trim()
  }
  descriptor.save()
}

def jenkinsThemeCSS = System.getenv('JENKINSTHEME_CSS_URL') ?: null
def jenkinsThemeJS  = System.getenv('JENKINSTHEME_JS_URL')  ?: null

def themeDecorator = Jenkins.getInstance().getDescriptor("org.codefirst.SimpleThemeDecorator")
configSimpleTheme(themeDecorator, jenkinsThemeCSS, jenkinsThemeJS)