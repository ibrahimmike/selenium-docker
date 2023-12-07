pipeline{
    agent any

    stages{
     stage('Build Jar'){
       steps{
        echo "doing mvn clean"
        echo "doing mvn package"
        sh "mvn clean package -DskipTests"

       }
     }
    stage('Build Image'){
       steps{
       echo "building docker image"
       sh "docker build -t=ibrahimshabana/selenium ."

       }
    }
    stage('Push image'){
    steps{
        echo "pushing docker image"
         sh "docker push ibrahimshabana/selenium"
    }
    }
    }
    post{
       always{
          echo "doing clean up"
       }
    }
}