# cicd-shared-lib — Jenkins Shared Library

A Jenkins Shared Library providing a reusable pipeline function for tagging and publishing 
Docker images to Docker Hub, avoiding duplicated logic across multiple Jenkinsfiles.

## What it does

The `dockerUtils` function:

1. Tags a locally built Docker image using a standardized naming convention: 
   `{dockerUser}/{repoName}:{image}-{tag}`
2. Authenticates to Docker Hub securely using Jenkins credentials (`withCredentials`), 
   avoiding hardcoded secrets in the pipeline
3. Pushes the tagged image to Docker Hub
4. Returns the final image name for use in later pipeline stages

## Usage

Referenced from a Jenkinsfile via:

```groovy
@Library('cicd-shared-lib') _

// ...

dockerUtils(
    image: DOCKER_IMAGE,
    tag: IMAGE_TAG,
    dockerUser: 'blackoctopus',
    repoName: 'epam-bootcamp-jnks-lab'
)
```

## Related project

This library is consumed by the [`epam-bootcamp-cicd-lab-jnks`](https://github.com/eche1984/epam-bootcamp-cicd-lab-jnks) 
pipeline, which builds, lints, security-scans, and deploys a Node.js application using this 
shared function for the image publishing step.

## Context

Built as part of the EPAM DevOps bootcamp to practice Jenkins Shared Library patterns — 
centralizing reusable pipeline logic instead of duplicating steps (like Docker tagging and 
authenticated registry push) across multiple Jenkinsfiles.
