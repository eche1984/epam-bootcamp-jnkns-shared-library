def call(Map config) {
    
    def dockerImageName = "${config.dockerUser}/${config.repoName}:${config.image}-${config.tag}"
    
    sh "docker tag ${config.image}:${config.tag} ${dockerImageName}"
    
    withCredentials([usernamePassword(
        credentialsId: 'docker-hub-credentials', 
        usernameVariable: 'DOCKER_USER', 
        passwordVariable: 'DOCKER_PASS'
    )]) {
        sh "echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin"
        sh "docker push ${dockerImageName}"
    }
    
    return dockerImageName
}

