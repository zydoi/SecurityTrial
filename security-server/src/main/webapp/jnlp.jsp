<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="application/x-java-jnlp-file" pageEncoding="UTF-8"%>

<jnlp codebase="http://localhost:8080/securitytrial">
    <information>
        <title>Simple Security Demo Application</title>
        <vendor>Ethan</vendor>
        <publisher>Ethan</publisher>
    </information>

    <resources>
        <j2se version="1.6+" />
        <property name="sid" value="<%=request.getSession().getId()%>" />
        <property name="serviceHost" value="<%=request.getServerName()%>"/>
        <property name="servicePort" value="<%=request.getServerPort()%>"/> 
        <jar href="security-demo.jar" main="true" download="eager" />
        <jar href="security-remote.jar" main="false" download="eager" />
        
    </resources>

    <application-desc main-class="org.ethan.security.demo.DemoApplication" />
</jnlp>