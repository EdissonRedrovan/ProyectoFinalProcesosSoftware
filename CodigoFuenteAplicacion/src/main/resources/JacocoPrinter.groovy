/* groovylint-disable CompileStatic, DuplicateNumberLiteral, DuplicateStringLiteral, JavaIoPackageAccess, LineLength */
paramEnvironment = System.getProperty('file') ?: ''
println "FILE LOAD FROM: ${paramEnvironment}"
reportFile = new File(paramEnvironment)

red = '\033[0;31m'
white = '\033[0;37m'
green = '\033[0;32m'
blue = '\033[0;34m'

if (!reportFile.exists() || !reportFile.canRead()) {
    println red + '[JACOCO] Skipped due to missing report file.' + white
    return
}
print "${white}"
print "${white}[JACOCO]${blue} |===========================================================================================|" println white
print "${white}[JACOCO]${blue} |                  REPORTE DE PORCENTAJE DE COVERTURA DE PRUEBAS UNITARIAS                  |" println white
print "${white}[JACOCO]${blue} |===========================================================================================|" println white
print "${white}[JACOCO]${blue} | ESTADO | CLASE                                                                   |   %    |" println white
print "${white}[JACOCO]${blue} |-------------------------------------------------------------------------------------------|" println white
reportFile.withReader('UTF-8') { reader ->
    /* groovylint-disable-next-line UnnecessaryGetter */
    html = getParserData().parseText(reader.readLine())
    body = html.body.table.tbody.tr
    body.each { n ->
        text = n.td[2]
        value = "${text}".replace('%', '')
        i = value.substring(0, value.length()) as int
        print"${white}[JACOCO]${blue} "
        if (i >= 90) {
            printf('|%-22s|', "${green} OK${blue} ")
            printf('%-80s', "${blue} ${n.td[0]} ")
            printf('|%-22s|', "${green} ${n.td[2]}${blue} ")
        } else {
            printf('|%-22s|', "${red} ERROR${blue} ")
            printf('%-80s', "${blue} ${n.td[0]} ")
            printf('|%-22s|', "${red} ${n.td[2]}${blue} ")
        }
        println white
        print "${white}[JACOCO]${blue} |-------------------------------------------------------------------------------------------|" println white
    }
    totalRow = html.body.table.tfoot.tr
    text = totalRow.td[2]
    value = "${text}".replace('%', '')
    i = value.substring(0, value.length()) as int
    print"${white}[JACOCO]${blue} "
    if (i >= 90) {
        printf('|%-22s|', "${green} OK${blue} ")
        printf('%-80s', "${blue} TOTAL ")
        printf('|%-22s|', "${green} ${totalRow.td[2]}${blue} ")
    } else {
        printf('|%-22s|', "${red} ERROR${blue} ")
        printf('%-80s', "${blue} TOTAL ")
        printf('|%-22s|', "${red} ${totalRow.td[2]}${blue} ")
    }
    println white
    print "${white}[JACOCO]${blue} |===========================================================================================|" println white
    print "${white}[JACOCO]${blue} |                                         FIN DEL REPORTE                                   |" println white
    print "${white}[JACOCO]${blue} |===========================================================================================|" println white
    print "${white}"
}

XmlSlurper getParserData() {
    parser = new XmlSlurper()
    parser.setFeature('http://apache.org/xml/features/disallow-doctype-decl', false)
    parser.setFeature('http://apache.org/xml/features/nonvalidating/load-external-dtd', false)
    return parser
}