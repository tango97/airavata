<!--Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE file
	distributed with this work for additional information regarding copyright ownership. The ASF licenses this file to you under
	the Apache License, Version 2.0 (theÏ "License"); you may not use this file except in compliance with the License. You may
	obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to
	in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF
	ANY ~ KIND, either express or implied. See the License for the specific language governing permissions and limitations under
	the License. -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ns="http://airavata.apache.org/gsi/ssh/2012/12">
<xsl:output method="text" />
<xsl:template match="/ns:JobDescriptor">
    <xsl:param name="quote">"</xsl:param>
    #! /bin/bash
# Grid Engine batch job script built by Apache Airavata
#   <xsl:choose>
    <xsl:when test="ns:shellName">
#$ -S <xsl:value-of select="ns:shellName"/>
    </xsl:when></xsl:choose>
#$ -V
    <xsl:choose>
    <xsl:when test="ns:queueName">
#$ -q <xsl:value-of select="ns:queueName"/>
    </xsl:when>
    </xsl:choose>
    <xsl:choose>
    <xsl:when test="ns:mailOptions">
#$ -m <xsl:value-of select="ns:mailOptions"/>
    </xsl:when>
    </xsl:choose>
    <xsl:choose>
<xsl:when test="ns:acountString">
#$ -A <xsl:value-of select="ns:acountString"/>
    </xsl:when>
    </xsl:choose>
    <xsl:choose>
    <xsl:when test="ns:maxWallTime">
#$ -l h_rt=<xsl:value-of select="ns:maxWallTime"/>
    </xsl:when>
    </xsl:choose>
    <xsl:choose>
    <xsl:when test="ns:jobName">
#$ -N <xsl:value-of select="ns:jobName"/>
    </xsl:when>
    </xsl:choose>
    <xsl:choose>
    <xsl:when test="ns:standardOutFile">
#$ -o <xsl:value-of select="ns:standardOutFile"/>
    </xsl:when>
    </xsl:choose>
    <xsl:choose>
    <xsl:when test="ns:standardOutFile">
#$ -e <xsl:value-of select="ns:standardErrorFile"/>
    </xsl:when>
    </xsl:choose>
    <xsl:choose>
    <xsl:when test="(ns:nodes) and (ns:processesPerNode)">
#$ -pe <xsl:value-of select="ns:processesPerNode"/>way <xsl:value-of select="12 * ns:nodes"/>
<xsl:text>&#xa;</xsl:text>
    </xsl:when>
    </xsl:choose>
<xsl:for-each select="ns:exports/ns:name">
<xsl:value-of select="."/>=<xsl:value-of select="./@value"/><xsl:text>&#xa;</xsl:text>
export<xsl:text>   </xsl:text><xsl:value-of select="."/>
<xsl:text>&#xa;</xsl:text>
</xsl:for-each>
<xsl:for-each select="ns:preJobCommands/ns:command">
      <xsl:value-of select="."/><xsl:text>   </xsl:text>
    </xsl:for-each>
cd <xsl:text>   </xsl:text><xsl:value-of select="ns:workingDirectory"/><xsl:text>&#xa;</xsl:text>
    <xsl:choose><xsl:when test="ns:jobSubmitterCommand">
<xsl:value-of select="ns:jobSubmitterCommand"/><xsl:text>   </xsl:text></xsl:when></xsl:choose><xsl:value-of select="ns:executablePath"/><xsl:text>   </xsl:text>
<xsl:for-each select="ns:inputs/ns:input">
      <xsl:value-of select="."/><xsl:text>   </xsl:text>
    </xsl:for-each>
<xsl:for-each select="ns:postJobCommands/ns:command">
      <xsl:value-of select="."/><xsl:text>   </xsl:text>
</xsl:for-each>
    ~/rabbitmq-java-client-bin-3.3.5/runjava.sh com.rabbitmq.examples.SimpleProducer amqp://<xsl:value-of select="ns:callBackIp"/><xsl:text> </xsl:text><xsl:value-of select="ns:userName"/>,<xsl:value-of select="ns:jobName"/><xsl:text> </xsl:text><xsl:value-of select="$quote"/><xsl:value-of select="$quote"/><xsl:text> </xsl:text><xsl:value-of select="ns:callBackPort"/>
</xsl:template>
</xsl:stylesheet>