node {
    def name = java.net.URLDecoder.decode(env.JOB_NAME, "UTF-8");
    try{
        slackSend color: 'good', message: "Downloading ${name}"

        // Mark the code checkout 'stage'....
        stage 'Checkout'

        //Clone
        checkout scm

        def gradleHome = tool 'Gradle 2.12'
        slackSend color: 'good', message: "Building ${name}"
        stage 'Build'

        def tasks = [:]

        /*tasks["Analyse"] = {
             step([$class: 'SonarRunnerBuilder', sonarScannerName: 'SonarQube 2.5.1'])
        }*/

        tasks["Compile"] ={
            sh "${gradleHome}/bin/gradle clean build"
            step([$class: 'JUnitResultArchiver', testResults: '**/build/test-results/TEST-*.xml'])
        }

        parallel tasks
        slackSend color: 'good', message: "Publishing ${name}"
        stage 'Publish'
        withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'Artifactory', passwordVariable: 'arespass', usernameVariable: 'aresuser']]) {
            sh "${gradleHome}/bin/gradle publish"
        }
    }catch(err){
        echo "Caught: ${err}"
        currentBuild.result = 'FAILURE'
        slackSend color: 'danger', message: "Error while building ${env.JOB_NAME}"
    }finally {     	
        step($class: 'InfluxDbPublisher')     	
        stage 'clean'         
        step([$class: 'WsCleanup'])     
    }
}
