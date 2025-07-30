FROM quay.io/quarkus/ubi9-quarkus-mandrel-builder-image:23.1-java21

USER root

# Installe des outils de base
RUN microdnf install -y \
    bash \
    git \
    unzip \
    sudo \
    which \
 && microdnf clean all

# Crée un user pour VS Code
RUN useradd -ms /bin/bash vscode && echo "vscode ALL=(ALL) NOPASSWD:ALL" >> /etc/sudoers
USER vscode

WORKDIR /workspace
