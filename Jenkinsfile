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
        environment{
         DOCKER_HUB = credentials('dockerhub-credentials')
        }
        sh 'docker login -u ${DOCKER_HUB_USR} -p ${DOCKER_HUB_PSW}'
         sh "docker push ibrahimshabana/selenium"
    }
    }
    }
    post{
       always{
          sh "docker logout"
       }
    }
}