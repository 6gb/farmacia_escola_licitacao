FROM jboss/wildfly:24.0.0.Final
EXPOSE 8080
ADD /target/farmacia_escola_licitacao.war /opt/jboss/wildfly/standalone/deployments/