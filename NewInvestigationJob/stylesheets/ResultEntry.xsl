<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes"/>
<xsl:param name="workOrder">0</xsl:param>
<xsl:param name="workOrderIndex">-</xsl:param>
<xsl:param name="multisessionid">0</xsl:param>
<xsl:param name="workOrderGroupIndex">-</xsl:param>
<xsl:template match="/">
	<xsl:param name="elementsequence">0</xsl:param>
	<xsl:for-each select="test">
	<div class="content">
    <xsl:for-each select="requisitionform">
    <xsl:element name="div">
    <xsl:attribute name="class">tab</xsl:attribute>
    <xsl:attribute name="style">cursor:pointer</xsl:attribute>
    <xsl:element name="a"><xsl:attribute name="href">javascript:showRequisitionForm('requisitionForm<xsl:value-of select="$workOrder"/><xsl:value-of select="$multisessionid"/><xsl:value-of select="$workOrderIndex"/>',this)</xsl:attribute><font color='white'> Requisition Form</font></xsl:element>
    </xsl:element>
    <xsl:for-each select="testtemplate">
    <xsl:element name="div">
        <xsl:attribute name="class">box</xsl:attribute>
        <xsl:attribute name="id">requisitionForm<xsl:value-of select="$workOrder"/><xsl:value-of select="$multisessionid"/><xsl:value-of select="$workOrderIndex"/></xsl:attribute>
        <xsl:attribute name="style">display:none</xsl:attribute>
        <xsl:for-each select="table">
                <xsl:element name="table">
                <xsl:attribute name="width">100%</xsl:attribute>
                        <xsl:for-each select="rowDetails"> 
                            <xsl:element name="tr">
                                <xsl:for-each select="columnDetails">
                                    <xsl:element name="td">
                                    <xsl:attribute name="colspan"><xsl:value-of select="@colspan"/></xsl:attribute>
                                    <xsl:attribute name="class"><xsl:value-of select="@class"/></xsl:attribute>
                                    <xsl:attribute name="width"><xsl:value-of select="@width"/></xsl:attribute>
                                        <xsl:element name="div">
                                                <xsl:attribute name="align"><xsl:value-of select="@align"/></xsl:attribute>
                                                    <xsl:for-each select="table">
                                                    <xsl:element name="table">
                                                    <xsl:attribute name="width">100%</xsl:attribute>
                                                        <xsl:for-each select="tr">
                                                        <xsl:element name="tr">
                                                        <xsl:for-each select="td">
                                                            <xsl:element name="td">
                                                                <xsl:for-each select="element">
                                                                    <xsl:if test="@idC = 'input'">
                                                                        <xsl:call-template name="INPUTREPORT" >
                                                                        <xsl:with-param name="tavIndex" select="$elementsequence + 1"/>
                                                                        </xsl:call-template>
                                                                    </xsl:if>
                                                                    <xsl:if test="@idC = 'textArea'">
                                                                        <xsl:call-template name="TextAreaREPORT">
                                                                        <xsl:with-param name="tavIndex"  select="$elementsequence + 1"/>
                                                                        </xsl:call-template>

                                                                    </xsl:if>
                                                                    <xsl:if test="@idC = 'Select'">
                                                                        <xsl:call-template name="SelectREPORT">
                                                                        <xsl:with-param name="tavIndex"><xsl:value-of select="$elementsequence+1"/></xsl:with-param>
                                                                        </xsl:call-template>
                                                                    </xsl:if>
                                                                    <xsl:if test="@idC = 'label'">
                                                                        <xsl:call-template name="TextREPORT"/>
                                                                    </xsl:if>
                                                                    <xsl:if test="@idC = 'button'">
                                                                    <xsl:attribute name="id"><xsl:value-of select="$workOrder"/><xsl:value-of select="@name"/></xsl:attribute>
                                                                    <xsl:call-template name="buttonReport"/>
                                                                    </xsl:if>
                                                                    <xsl:if test="@idC = 'queryValue'">
                                                                        <xsl:call-template name="queryValue"/>
                                                                </xsl:if>
                                                                    <xsl:if test="@idC = 'imagesection'">
                                                                    <xsl:element name="div">
                                                                        <xsl:call-template name="createrequisitionsection"><xsl:with-param name="elementCode"><xsl:value-of select="@name"/></xsl:with-param><xsl:with-param name="listIndex"><xsl:value-of select="$workOrderIndex"/></xsl:with-param><xsl:with-param name="listgroupIndex"><xsl:value-of select="$workOrderGroupIndex"/></xsl:with-param></xsl:call-template>
                                                                    </xsl:element>
                                                                </xsl:if>

                                                                    <xsl:for-each select="rangetag">
                                                                        <xsl:element name="b">
                                                                            <xsl:call-template name="DISPLAYRANGETAG"/>
                                                                        </xsl:element>
                                                                    </xsl:for-each>
                                                            </xsl:for-each>
                                                            </xsl:element>
                                                        </xsl:for-each>

                                                        </xsl:element>
                                                        </xsl:for-each>
                                                        </xsl:element>
                                                    </xsl:for-each>
                                        </xsl:element>
                                    </xsl:element>
                                    </xsl:for-each>
                            </xsl:element>
                        </xsl:for-each>
                    </xsl:element>
            
                </xsl:for-each>    
            </xsl:element>    
        </xsl:for-each>
    </xsl:for-each>

    <xsl:for-each select="sampleform">
        <xsl:for-each select="testtemplate">
    <xsl:element name="div">
    <xsl:attribute name="class">tab</xsl:attribute>
    <xsl:attribute name="style">cursor:pointer</xsl:attribute>
    <xsl:element name="a"><xsl:attribute name="href">javascript:showRequisitionForm('sampleCollectionForm<xsl:value-of select="$workOrder"/><xsl:value-of select="$multisessionid"/><xsl:value-of select="$workOrderIndex"/>',this)</xsl:attribute><font color='white'> Sample Collection Form</font></xsl:element>
    </xsl:element>
    <xsl:element name="div">
        <xsl:attribute name="class">box</xsl:attribute>
        <xsl:attribute name="id">sampleCollectionForm<xsl:value-of select="$workOrder"/><xsl:value-of select="$multisessionid"/><xsl:value-of select="$workOrderIndex"/></xsl:attribute>
        <xsl:attribute name="style">display:none;</xsl:attribute>
        <xsl:for-each select="table">
                <xsl:element name="table">
                
                <xsl:attribute name="width">100%</xsl:attribute>
                        <xsl:for-each select="rowDetails"> 
                            <xsl:element name="tr">
                                <xsl:for-each select="columnDetails">
                                    <xsl:element name="td">
                                    <xsl:attribute name="colspan"><xsl:value-of select="@colspan"/></xsl:attribute>
                                    <xsl:attribute name="class"><xsl:value-of select="@class"/></xsl:attribute>
                                    <xsl:attribute name="width"><xsl:value-of select="@width"/></xsl:attribute>
                                        <xsl:element name="div">
                                                <xsl:attribute name="align"><xsl:value-of select="@align"/></xsl:attribute>
                                                    <xsl:for-each select="table">
                                                    <xsl:element name="table">
                                                        <xsl:for-each select="tr">
                                                        <xsl:element name="tr">
                                                        <xsl:for-each select="td">
                                                            <xsl:element name="td">
                                                                <xsl:for-each select="element">
                                                                    <xsl:if test="@idC = 'input'">
                                                                        <xsl:call-template name="INPUTREPORT" >
                                                                        <xsl:with-param name="tavIndex" select="$elementsequence + 1"/>
                                                                        </xsl:call-template>
                                                                    </xsl:if>
                                                                    <xsl:if test="@idC = 'textArea'">
                                                                        <xsl:call-template name="TextAreaREPORT">
                                                                        <xsl:with-param name="tavIndex"  select="$elementsequence + 1"/>
                                                                        </xsl:call-template>

                                                                    </xsl:if>
                                                                    <xsl:if test="@idC = 'Select'">
                                                                        <xsl:call-template name="SelectREPORT">
                                                                        <xsl:with-param name="tavIndex"><xsl:value-of select="$elementsequence+1"/></xsl:with-param>
                                                                        </xsl:call-template>
                                                                    </xsl:if>
                                                                    <xsl:if test="@idC = 'button'">
                                                                    <xsl:attribute name="id"><xsl:value-of select="$workOrder"/><xsl:value-of select="@name"/></xsl:attribute>
                                                                    <xsl:call-template name="buttonReport"/>
                                                                    </xsl:if>
                                                                    <xsl:if test="@idC = 'queryValue'">
                                                                        <xsl:call-template name="queryValue"/>
                                                                </xsl:if>
                                                                    <xsl:if test="@idC = 'imagesection'">
                                                                    <xsl:element name="div">
                                                                        <xsl:call-template name="createrequisitionsection"><xsl:with-param name="elementCode"><xsl:value-of select="@name"/></xsl:with-param><xsl:with-param name="listIndex"><xsl:value-of select="$workOrderIndex"/></xsl:with-param><xsl:with-param name="listgroupIndex"><xsl:value-of select="$workOrderGroupIndex"/></xsl:with-param></xsl:call-template>
                                                                    </xsl:element>
                                                                </xsl:if>
                                                                    <xsl:if test="@idC = 'label'">
                                                                        <xsl:call-template name="TextREPORT"/>
                                                                    </xsl:if>
                                                                    <xsl:for-each select="rangetag">
                                                                        <xsl:element name="b">
                                                                            <xsl:call-template name="DISPLAYRANGETAG"/>
                                                                        </xsl:element>
                                                                    </xsl:for-each>
                                                            </xsl:for-each>
                                                            </xsl:element>
                                                        </xsl:for-each>

                                                        </xsl:element>
                                                        </xsl:for-each>
                                                        </xsl:element>
                                                    </xsl:for-each>
                                        </xsl:element>
                                    </xsl:element>
                                    </xsl:for-each>
                            </xsl:element>
                        </xsl:for-each>
                    </xsl:element>
                </xsl:for-each>    
                </xsl:element>
            </xsl:for-each>
    </xsl:for-each>
    
    <xsl:element name="div">
    <xsl:attribute name="class">tab</xsl:attribute>
    <xsl:attribute name="style">cursor:pointer</xsl:attribute>
    <font color='white'>Result Entry Form</font>
    </xsl:element>
    <div class="box">
    <xsl:for-each select="testtemplate">
        <xsl:for-each select="table">
                <xsl:element name="table">
                <xsl:attribute name="width">100%</xsl:attribute>
                        <xsl:for-each select="rowDetails"> 
                            <xsl:element name="tr">
                                <xsl:for-each select="columnDetails">
                                    <xsl:element name="td">
                                    <xsl:attribute name="class"><xsl:value-of select="@class"/></xsl:attribute>
                                    <xsl:attribute name="width"><xsl:value-of select="@width"/></xsl:attribute>
                                    <xsl:attribute name="colspan"><xsl:value-of select="@colspan"/></xsl:attribute>
                                        <xsl:element name="div">
                                                <xsl:attribute name="align"><xsl:value-of select="@align"/></xsl:attribute>
                                                    <xsl:for-each select="table">
                                                    <xsl:element name="table">
                                                    <xsl:attribute name="width">100%</xsl:attribute>
                                                        <xsl:for-each select="tr">
                                                        <xsl:element name="tr">
                                                        <xsl:for-each select="td">
                                                            <xsl:element name="td">
                                                                <xsl:for-each select="element">
                                                                    <xsl:if test="@idC = 'input'">
                                                                        <xsl:call-template name="INPUT" >
                                                                        <xsl:with-param name="tavIndex" select="$elementsequence + 1"/>
                                                                        </xsl:call-template>
                                                                    </xsl:if>
                                                                    <xsl:if test="@idC = 'textArea'">
                                                                        <xsl:call-template name="TextArea">
                                                                        <xsl:with-param name="tavIndex"  select="$elementsequence + 1"/>
                                                                        </xsl:call-template>

                                                                    </xsl:if>
                                                                    <xsl:if test="@idC = 'Select'">
                                                                        <xsl:call-template name="Select">
                                                                        <xsl:with-param name="tavIndex"><xsl:value-of select="$elementsequence+1"/></xsl:with-param>
                                                                        </xsl:call-template>
                                                                    </xsl:if>
                                                                    <xsl:if test="@idC = 'label'">
                                                                        <xsl:call-template name="Text"/>
                                                                    </xsl:if>
                                                                    <xsl:if test="@idC = 'button'">
                                                                    <xsl:attribute name="id">td#<xsl:value-of select="@name"/></xsl:attribute>
                                                                    <xsl:call-template name="button"><xsl:with-param name="index"><xsl:value-of select="$workOrderIndex"/></xsl:with-param><xsl:with-param name="listgroupIndex"><xsl:value-of select="$workOrderGroupIndex"/></xsl:with-param></xsl:call-template>
                                                                </xsl:if>

                                                                <xsl:if test="@idC = 'queryValue'">
                                                                        <xsl:call-template name="queryValue"/>
                                                                </xsl:if>
                                                                <xsl:if test="@idC = 'imagesection'">
                                                                    <xsl:attribute name="id">td#<xsl:value-of select="@name"/></xsl:attribute>
                                                                    <xsl:call-template name="imagesection"><xsl:with-param name="index"><xsl:value-of select="$workOrderIndex"/></xsl:with-param><xsl:with-param name="listgroupIndex"><xsl:value-of select="$workOrderGroupIndex"/></xsl:with-param></xsl:call-template>
                                                                </xsl:if>
                                                                    <xsl:for-each select="rangetag">
                                                                        <xsl:element name="b">
                                                                            <xsl:call-template name="DISPLAYRANGETAG"/>
                                                                        </xsl:element>
                                                                    </xsl:for-each>
                                                            </xsl:for-each>
                                                            </xsl:element>
                                                        </xsl:for-each>

                                                        </xsl:element>
                                                        </xsl:for-each>
                                                        </xsl:element>
                                                    </xsl:for-each>
                                        </xsl:element>
                                    </xsl:element>
                                    </xsl:for-each>
                            </xsl:element>
                        </xsl:for-each>
                    </xsl:element>
                </xsl:for-each>    
            </xsl:for-each>
    </div>
    </div>
    </xsl:for-each>

    
    <xsl:for-each select="testgroup">
    <div class="content">
    <xsl:element name="div">
    <xsl:attribute name="class">tab</xsl:attribute>
    <xsl:attribute name="style">cursor:pointer</xsl:attribute>
    <font color='white'>Test Group Details</font>
    </xsl:element>
    <div class="box">
    <xsl:for-each select="testtemplate">
        <xsl:for-each select="table">
                <xsl:element name="table">
                <xsl:attribute name="width">100%</xsl:attribute>
                        <xsl:for-each select="rowDetails"> 
                            <xsl:element name="tr">
                                <xsl:for-each select="columnDetails">
                                    <xsl:element name="td">
                                    <xsl:attribute name="class"><xsl:value-of select="@class"/></xsl:attribute>
                                    <xsl:attribute name="width"><xsl:value-of select="@width"/></xsl:attribute>
                                    <xsl:attribute name="colspan"><xsl:value-of select="@colspan"/></xsl:attribute>
                                        <xsl:element name="div">
                                                <xsl:attribute name="align"><xsl:value-of select="@align"/></xsl:attribute>
                                                    <xsl:for-each select="table">
                                                    <xsl:element name="table">
                                                    <xsl:attribute name="width">100%</xsl:attribute>
                                                        <xsl:for-each select="tr">
                                                        <xsl:element name="tr">
                                                        <xsl:for-each select="td">
                                                            <xsl:element name="td">
                                                                <xsl:for-each select="element">
                                                                    <xsl:if test="@idC = 'input'">
                                                                        <xsl:call-template name="INPUT" >
                                                                        <xsl:with-param name="tavIndex" select="$elementsequence + 1"/>
                                                                        </xsl:call-template>
                                                                    </xsl:if>
                                                                    <xsl:if test="@idC = 'textArea'">
                                                                        <xsl:call-template name="TextArea">
                                                                        <xsl:with-param name="tavIndex"  select="$elementsequence + 1"/>
                                                                        </xsl:call-template>

                                                                    </xsl:if>
                                                                    <xsl:if test="@idC = 'Select'">
                                                                        <xsl:call-template name="Select">
                                                                        <xsl:with-param name="tavIndex"><xsl:value-of select="$elementsequence+1"/></xsl:with-param>
                                                                        </xsl:call-template>
                                                                    </xsl:if>
                                                                    <xsl:if test="@idC = 'label'">
                                                                        <xsl:call-template name="Text"/>
                                                                    </xsl:if>
                                                                    <xsl:if test="@idC = 'button'">
                                                                    <xsl:attribute name="id">td#<xsl:value-of select="@name"/></xsl:attribute>
                                                                    <xsl:call-template name="button"><xsl:with-param name="index"><xsl:value-of select="$workOrderIndex"/></xsl:with-param></xsl:call-template>
                                                                </xsl:if>

                                                                <xsl:if test="@idC = 'queryValue'">
                                                                        <xsl:call-template name="queryValue"/>
                                                                </xsl:if>
                                                                <xsl:if test="@idC = 'imagesection'">
                                                                    <xsl:attribute name="id">td#<xsl:value-of select="@name"/></xsl:attribute>
                                                                    <xsl:call-template name="imagesection"><xsl:with-param name="index"><xsl:value-of select="$workOrderIndex"/></xsl:with-param></xsl:call-template>
                                                                </xsl:if>
                                                                    <xsl:for-each select="rangetag">
                                                                        <xsl:element name="b">
                                                                            <xsl:call-template name="DISPLAYRANGETAG"/>
                                                                        </xsl:element>
                                                                    </xsl:for-each>
                                                            </xsl:for-each>
                                                            </xsl:element>
                                                        </xsl:for-each>

                                                        </xsl:element>
                                                        </xsl:for-each>
                                                        </xsl:element>
                                                    </xsl:for-each>
                                        </xsl:element>
                                    </xsl:element>
                                    </xsl:for-each>
                            </xsl:element>
                        </xsl:for-each>
                    </xsl:element>
                </xsl:for-each>    
            </xsl:for-each>
    </div>
    </div>


    
    </xsl:for-each>
    
    </xsl:template>

<xsl:template name="getOrganism">
	<xsl:param name="selectedValue">0</xsl:param>
  		<!-- <xsl:value-of select="Hello:getHello()"/>-->
		 <xsl:for-each select="document('organism108.xml')/organism/option">
		 <xsl:if test="$selectedValue != @value">
					<xsl:element name="option"><xsl:attribute name="value"><xsl:value-of select="@value"/></xsl:attribute><xsl:value-of select="text()"/> </xsl:element>
				</xsl:if>
				<xsl:if test="$selectedValue = @value">
					<xsl:element name="option"><xsl:attribute name="selected">selected</xsl:attribute><xsl:attribute name="value"><xsl:value-of select="@value"/></xsl:attribute><xsl:value-of select="text()"/> </xsl:element>
				</xsl:if>
		 
		 </xsl:for-each>
</xsl:template>
<xsl:template name="INPUT" >
	<xsl:param name="tavIndex" ></xsl:param>
	<xsl:element name="input">
		<xsl:attribute name="tabIndex"><xsl:value-of select="$tavIndex"/> </xsl:attribute>
			<xsl:if test="@type!=''">
				<xsl:attribute name="Type">
					<xsl:value-of select="@type"/>
				</xsl:attribute>
			</xsl:if>
			<xsl:if test="@name!=''">
			<xsl:attribute name="name">
				<xsl:value-of select="@name"/>
			</xsl:attribute>
			</xsl:if>
			<xsl:attribute name="onFocus">callOnFocus(this)</xsl:attribute>
			<xsl:if test="@value!=''">
			<xsl:attribute name="value">
				<xsl:value-of select="@value"/>
			</xsl:attribute>
			</xsl:if>
			<xsl:if test="@value=''">
			<xsl:attribute name="value">
				<xsl:value-of select="@defaultValue"/>
			</xsl:attribute>
			</xsl:if>
			<xsl:if test="@onClick!=''">	
			<xsl:attribute name="onclick">
				<xsl:value-of select="@onClick"/>
			</xsl:attribute>
			</xsl:if>
			<xsl:if test="@class!=''">	
			<xsl:attribute name="class">
				<xsl:value-of select="@class"/>
			</xsl:attribute>
			</xsl:if>
			<xsl:if test="@onKeyPress!=''">	
			<xsl:attribute name="onKeyPress">
				<xsl:value-of select="@onKeyPress"/>
			</xsl:attribute>
			</xsl:if>
			<xsl:if test="@maxlength!=''">	
			<xsl:attribute name="maxlength">
				<xsl:value-of select="@maxlength"/>
			</xsl:attribute>
			</xsl:if>
			<xsl:if test="@src!=''">	
			<xsl:attribute name="src">
				<xsl:value-of select="@src"/>
			</xsl:attribute>
			</xsl:if>

			<xsl:if test="@eventName = '0'">
			<xsl:attribute name="onKeyPress">
					<xsl:value-of select="@eventValidationString"/>
			</xsl:attribute>
			</xsl:if>
			
			<xsl:if test="@eventName = '1'">
			<xsl:attribute name="onClick">
					<xsl:value-of select="@eventValidationString"/>
			</xsl:attribute>
			</xsl:if>

			<xsl:if test="@eventName = '2'">
			<xsl:attribute name="onBlur">
				<xsl:value-of select="@eventValidationString"/>
			</xsl:attribute>
			</xsl:if>

			<xsl:if test="@eventName = '3'">
			<xsl:attribute name="onChange">
					<xsl:value-of select="@eventValidationString"/>
			</xsl:attribute>
			</xsl:if>
	</xsl:element>
	</xsl:template>

<xsl:template name="TextArea">
	<xsl:param name="tavIndex">0</xsl:param>
		<xsl:element name="textarea">
			<xsl:attribute name="tabIndex"><xsl:value-of select="$tavIndex"/></xsl:attribute>
			<xsl:attribute name="onFocus">callOnFocus(this)</xsl:attribute>
			<xsl:if test="@name !='' ">
			<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
			<xsl:attribute name="id"><xsl:value-of select="@name"/></xsl:attribute>
			</xsl:if>
			<xsl:if test="@rows!=''">
			<xsl:attribute name="rows"><xsl:value-of select="@rows"/></xsl:attribute>
			</xsl:if>
			<xsl:if test="@cols!=''">
			<xsl:attribute name="cols"><xsl:value-of select="@cols"/></xsl:attribute>
			</xsl:if>
			<xsl:if test="@onClick!=''">	
			<xsl:attribute name="onclick"><xsl:value-of select="@onClick"/></xsl:attribute>
			</xsl:if>
			<xsl:if test="@onKeyPress!=''">	
			<xsl:attribute name="onKeyPress"><xsl:value-of select="@onKeyPress"/></xsl:attribute>
			</xsl:if>
			<xsl:if test="@class!=''">	
			<xsl:attribute name="class"><xsl:value-of select="@class"/></xsl:attribute>
			</xsl:if>
			<xsl:if test="@maxlength!=''">	
			<xsl:attribute name="maxlength"><xsl:value-of select="@maxlength"/></xsl:attribute>
			</xsl:if>

			
			
			<xsl:if test="@eventName = '0'">
			<xsl:attribute name="onKeyPress"><xsl:value-of select="@eventValidationString"/></xsl:attribute>
			</xsl:if>

			<xsl:if test=" @eventName= '0'">
			<xsl:attribute name="{@eventName}"><xsl:value-of select="@eventValidationString"/></xsl:attribute>
			</xsl:if>
			
			
			<xsl:if test="@eventName = '1'">
			<xsl:attribute name="onClick"><xsl:value-of select="@eventValidationString"/></xsl:attribute>
			</xsl:if>

			<xsl:if test="@eventName = '2'">
			<xsl:attribute name="onBlur"><xsl:value-of select="@eventValidationString"/></xsl:attribute>
			</xsl:if>

			<xsl:if test="@eventName = '3'">
			<xsl:attribute name="onChange"><xsl:value-of select="@eventValidationString"/></xsl:attribute>
			</xsl:if>
			<xsl:if test="@value!=''">
			<xsl:value-of select="@value"/>
			</xsl:if>
			<xsl:if test="@value=''">
			<xsl:value-of select="@defaultValue"/>
			</xsl:if>
		</xsl:element>
		<xsl:if test="@editor = '1'">
		<xsl:element name="script">
		<xsl:attribute name="language">javascript</xsl:attribute>
		WYSIWYG.attach('<xsl:value-of select="@name"/>',full);
		</xsl:element>
		</xsl:if>
	</xsl:template>

<xsl:template name="Select">
	<xsl:param name="tavIndex" />
		<xsl:element name="select">
		<xsl:attribute name="tabIndex">
			<xsl:value-of select="tavIndex"/>
		</xsl:attribute>

			<xsl:if test="@name!=''">
			<xsl:attribute name="name">
				<xsl:value-of select="@name"/>
			</xsl:attribute>
			</xsl:if>
			
			
			<xsl:variable name="val" select="@value"/>
            <xsl:variable name="defaultval" select="@defaultValue"/>
			<xsl:attribute name="value"><xsl:value-of select="$val"/></xsl:attribute>
			<xsl:if test="@onClick!=''">	
			<xsl:attribute name="onclick">
				<xsl:value-of select="@onClick"/>
			</xsl:attribute>
			</xsl:if>
			<xsl:if test="@onKeyPress!=''">	
			<xsl:attribute name="onKeyPress">
				<xsl:value-of select="@onKeyPress"/>
			</xsl:attribute>
			</xsl:if>
			<xsl:if test="@class!=''">	
			<xsl:attribute name="class">
				<xsl:value-of select="@class"/>
			</xsl:attribute>
			</xsl:if>
			<xsl:element name="option"><xsl:attribute name="value">-1</xsl:attribute>Select</xsl:element>
			<xsl:for-each select="options">
			<xsl:for-each select="option">
			<xsl:if test="$val != ''">
            <xsl:if test="$val != @value">
                <xsl:element name="option"><xsl:attribute name="value"><xsl:value-of select="@value"/></xsl:attribute><xsl:value-of select="@label"/></xsl:element>
            </xsl:if>
            <xsl:if test="$val = @value">
                <xsl:element name="option"><xsl:attribute name="selected">selected</xsl:attribute><xsl:attribute name="value"><xsl:value-of select="@value"/></xsl:attribute><xsl:value-of select="@label"/></xsl:element>
            </xsl:if>
            </xsl:if>
            <xsl:if test="$val = ''">
            <xsl:if test="$defaultval != @value">
                <xsl:element name="option"><xsl:attribute name="value"><xsl:value-of select="@value"/></xsl:attribute><xsl:value-of select="@label"/></xsl:element>
            </xsl:if>
            <xsl:if test="$defaultval = @value">
                <xsl:element name="option"><xsl:attribute name="selected">selected</xsl:attribute><xsl:attribute name="value"><xsl:value-of select="@value"/></xsl:attribute><xsl:value-of select="@label"/></xsl:element>
            </xsl:if>
            </xsl:if>    

			</xsl:for-each>
		</xsl:for-each>
		<xsl:if test="@eventName = '0'">
			<xsl:attribute name="onKeyPress">
					<xsl:value-of select="@eventValidationString"/>
			</xsl:attribute>
			</xsl:if>
			
			<xsl:if test="@eventName = '1'">
			<xsl:attribute name="onClick">
					<xsl:value-of select="@eventValidationString"/>
			</xsl:attribute>
			</xsl:if>

			<xsl:if test="@eventName = '2'">
			<xsl:attribute name="onBlur">
				<xsl:value-of select="@eventValidationString"/>
			</xsl:attribute>
			</xsl:if>

			<xsl:if test="@eventName = '3'">
			<xsl:attribute name="onChange">
					<xsl:value-of select="@eventValidationString"/>
			</xsl:attribute>
			</xsl:if>
		</xsl:element>
	</xsl:template>

<xsl:template name="Text">
	<xsl:element name="div">
	<xsl:attribute name="align"><xsl:value-of select="@align"/></xsl:attribute>
	<xsl:if test="@bold = '1'">
	<xsl:element name="b">
		<xsl:if test="@underline = '1'">
		<xsl:element name="u">
			<xsl:value-of select="@value"/>
		</xsl:element>
		</xsl:if>
		<xsl:if test="@underline = '0'">
			<xsl:value-of select="@value"/>
		</xsl:if>
		
	</xsl:element>
	</xsl:if>
	<xsl:if test="@bold = '0'">
		<xsl:if test="@underline = '1'">
			<xsl:element name="u">
				<xsl:value-of select="@value"/>
			</xsl:element>
		</xsl:if>
		<xsl:if test="@underline = '0'">
				<xsl:value-of select="@value"/>
		</xsl:if>
	</xsl:if>
	</xsl:element>

</xsl:template>

<xsl:template name="imagesection" >
 <xsl:param name="index"></xsl:param>
	<xsl:variable name="name"><xsl:value-of select="@name"/></xsl:variable>
	
	<xsl:element name="input">
			<xsl:if test="@idC!=''">
				<xsl:attribute name="Type">
					<xsl:value-of select="@idC"/>
				</xsl:attribute>
			</xsl:if>
			<xsl:if test="@name!=''">
			<xsl:attribute name="name">
				<xsl:value-of select="@name"/>
			</xsl:attribute>
			</xsl:if>
			<xsl:attribute name="onFocus">callOnFocus(this)</xsl:attribute>
			<xsl:if test="@value!=''">
			<xsl:attribute name="value"><xsl:value-of select="@value"/></xsl:attribute>
			</xsl:if>
			<xsl:attribute name="style">background-image: url('/AHIMS/hisglobal/images/blankButton.png');width:155px;height:25px;font-size:2px;font-weight:bold;text-align: center;</xsl:attribute>
			<xsl:attribute name="onclick">return openAntiMicrobialTest('<xsl:value-of select="@callUrl"/>',this,<xsl:value-of select="$index"/>);</xsl:attribute>
	</xsl:element>
	</xsl:template>

<xsl:template name="DISPLAYRANGETAG">
<font size="2">
<xsl:if test="@rangesymbol =''">
<xsl:if test="@rangeto = '' and @rangefrom = ''">--</xsl:if>

<xsl:if test="@rangeto != '' and @rangefrom != ''">
	<xsl:value-of select="@rangefrom"/>  - <xsl:value-of select="@rangeto"/><xsl:value-of select="@rangefromunit" disable-output-escaping="yes"/>
</xsl:if>

<xsl:if test="@rangefrom = '' and @rangeto != ''">
	&lt;<xsl:value-of select="@rangeto"/><xsl:value-of select="@rangetounit" disable-output-escaping="yes"/>
</xsl:if>

<xsl:if test="@rangeto = '' and @rangefrom != ''">
	&gt; <xsl:value-of select="@rangefrom"/><xsl:value-of select="@rangefromunit" disable-output-escaping="yes"/>
</xsl:if>
</xsl:if>
<xsl:if test="@rangesymbol !=''"><xsl:value-of select="@rangesymbol" disable-output-escaping="yes"/></xsl:if>

</font>
</xsl:template>

<xsl:template name="button" >
<xsl:param name="index"></xsl:param>
	<xsl:variable name="name"><xsl:value-of select="@name"/></xsl:variable>
	
	<xsl:element name="input">
			<xsl:if test="@idC!=''">
				<xsl:attribute name="Type">
					<xsl:value-of select="@idC"/>
				</xsl:attribute>
			</xsl:if>
			<xsl:if test="@name!=''">
			<xsl:attribute name="name">
				<xsl:value-of select="@name"/>
			</xsl:attribute>
			</xsl:if>
			<xsl:attribute name="onFocus">callOnFocus(this)</xsl:attribute>
			<xsl:if test="@value!=''">
			<xsl:attribute name="value"><xsl:value-of select="@value"/></xsl:attribute>
			</xsl:if>
			<xsl:attribute name="style">background-image: url('/AHIMS/hisglobal/images/blankButton.png');width:155px;height:25px;font-size:2px;font-weight:bold;text-align: center;</xsl:attribute>
			
			<xsl:attribute name="style">background-image: url('/AHIMS/hisglobal/images/blankButton.png');width:155px;height:25px;font-size:2px;font-weight:bold;text-align: center;</xsl:attribute>
			<xsl:if test="@callUrl ='/AHIMS/investigation/transaction/searchFunctionality.cnt'">
			<xsl:attribute name="onclick">return openSiteDiagnosis('<xsl:value-of select="@callUrl"/>',this);</xsl:attribute>
			</xsl:if>
			<xsl:if test="@callUrl ='/AHIMS/investigation/transaction/radiologicalFindings.cnt'">
			<xsl:attribute name="onclick">return openTestResults('<xsl:value-of select="@callUrl"/>',this);</xsl:attribute>
			</xsl:if>
			<xsl:if test="@callUrl ='/AHIMS/investigation/commonaction.cnt?mode=ORGANISMANTIBIOTIC'">
			<xsl:attribute name="onclick">return openAntiMicrobial('<xsl:value-of select="@callUrl"/>',this,<xsl:value-of select="$index"/>);</xsl:attribute>
			</xsl:if>

			<xsl:for-each select="investigations">
			<xsl:element name="div">
				<xsl:attribute name="id">divId_<xsl:value-of select="$name"/></xsl:attribute>
					<xsl:for-each select="investigation">
						<xsl:value-of select="text()" disable-output-escaping="yes"/>
					</xsl:for-each>
			</xsl:element>
		</xsl:for-each>
		
		<xsl:for-each select="sitediagnosisdetail">
			<xsl:element name="div">
				<xsl:attribute name="id">divId_<xsl:value-of select="$name"/></xsl:attribute>
				<xsl:element name="table">
				<xsl:attribute name="cellspacing">1px</xsl:attribute>
				<xsl:attribute name="cellpadding">1px</xsl:attribute>
				<xsl:attribute name="bordercolor">#FFB366</xsl:attribute>
				<xsl:attribute name="border">1px</xsl:attribute>
				<xsl:attribute name="width">100%</xsl:attribute>
				<xsl:element name="tr">
								<xsl:element name="td">
								<xsl:attribute name="width">30%</xsl:attribute>
								<xsl:attribute name="align">center</xsl:attribute>
								<div align="center"><b>Site</b></div>
								</xsl:element>
								<xsl:element name="td">
								<xsl:attribute name="width">70%</xsl:attribute>
								<xsl:attribute name="align">center</xsl:attribute>
								<div align="center"><b>Diagnosis</b></div>	
								</xsl:element>
						</xsl:element>


					<xsl:for-each select="site">
						<xsl:element name="tr">
								<xsl:element name="td">
								<xsl:attribute name="width">30%</xsl:attribute>
									<xsl:value-of select="@name"/>(<xsl:value-of select="@code" disable-output-escaping="no"/>)
								</xsl:element>
								<xsl:element name="td">
								<xsl:attribute name="width">70%</xsl:attribute>
								<xsl:for-each select="diagnosis">
									<xsl:value-of select="@name"/>(<xsl:value-of select="@code" disable-output-escaping="no"/>),
								</xsl:for-each>
								</xsl:element>
						</xsl:element>
					</xsl:for-each>
					<xsl:if test="not(current()/site)" >
						<xsl:element name="tr">
								<xsl:element name="td">
								<xsl:attribute name="width">100%</xsl:attribute>
								<xsl:attribute name="colspan">2</xsl:attribute>
									<div align="center"><font color="red"><b>No Site Diagnosis Found !(Please Enter Site Diagnosis By Clicking on Site Diagnosis Button)</b></font></div>
								</xsl:element>
						</xsl:element>
					</xsl:if>

					</xsl:element>
			</xsl:element>
		</xsl:for-each>


		<!-- anti microbial test results-->
		
			<xsl:for-each select="antisusceptibility">
			<xsl:element name="div">
				<xsl:attribute name="id">divId_<xsl:value-of select="$name"/></xsl:attribute>
				<table width="100%" cellspacing="1px" cellpadding="1px" bordercolor="#FFB366" border="1px"><tr><td width="100%">
				<table width="100%" cellspacing="1px" cellpadding="1px" bordercolor="#FFB366" border="1px">
							<tr><td width="34%"><div align="center"><b>Antibiotics / Organism </b></div></td>
						<xsl:for-each select="rows/organismrow">
							<xsl:element name="td"><xsl:attribute name="width"><xsl:value-of select="@width"/></xsl:attribute>
								<div align="center"><b><xsl:value-of select="@value"/></b></div>
							</xsl:element>
						</xsl:for-each>					
				</tr>
				</table>
				<xsl:for-each select="antibioticclasses">
						<xsl:for-each select="antibioticclass">
						<table width="100%" cellspacing="1px" cellpadding="1px" bordercolor="#FFB366" border="1px">
						<tr><td width="34%"><div align='left'><b><xsl:value-of select="@value"/></b></div></td></tr>
						<xsl:for-each select="antibiotic">
						<tr><td width="34%"><div align='right'><b><xsl:value-of select="@value"/></b></div></td>
							<xsl:for-each select="organism">
							<xsl:element name="td">
								<xsl:attribute name="width"><xsl:value-of select="@width"/></xsl:attribute>
								<div align='center'><xsl:value-of select="@data"/></div>	
							</xsl:element>
							</xsl:for-each>
						</tr>
						</xsl:for-each>
						</table>				
						</xsl:for-each>
				</xsl:for-each>

				
				</td>
				</tr>
				</table>			
            <table width="100%">
            <tr>
            <td width="100%">
            <fieldset><font size="2spx"><u>Interpretation:</u><p>S: Sensitive ,  R: Resistance and  MS: Moderately Sensitive,-:Not Done</p></font>
            </fieldset>
            </td>
              </tr></table>

			</xsl:element>
		</xsl:for-each>		
			

		</xsl:element>
	</xsl:template>

<xsl:template name="queryValue" >
	<xsl:element name="table">
	
	<xsl:attribute name="width">100%</xsl:attribute> 
	
		<xsl:for-each select="otherTemplate">
		<xsl:element name="tr">
			<xsl:element name="td"><xsl:attribute name="width">100%</xsl:attribute> 
			<fieldset>
			<xsl:value-of select="text()" disable-output-escaping="yes"/>
			</fieldset>
			</xsl:element>
		</xsl:element>
	</xsl:for-each>
	<xsl:for-each  select="headerDetails">
					<xsl:element name="tr">
						<xsl:for-each  select="header">
							<xsl:element name="td">
								<div align="center"><b><xsl:value-of select="@value"/></b></div>
							</xsl:element>
						</xsl:for-each>
					</xsl:element>
			</xsl:for-each>
	<xsl:for-each select="results">
			<xsl:for-each  select="resultrow">
					<xsl:element name="tr"> 
						<xsl:for-each  select="resultcolumn">
						<xsl:if test="@valueType = '0' or @valueType ='3'">
						<xsl:element name="td">
						<xsl:attribute name="width"><xsl:value-of select="columnwidth"/></xsl:attribute>
						<xsl:value-of select="@value" disable-output-escaping="yes"/>
						
						</xsl:element>
						</xsl:if>
						</xsl:for-each>
					</xsl:element>
			</xsl:for-each>
	<xsl:for-each select="errorResult">
			<xsl:element name="tr">
				<xsl:element name="td">
				<xsl:attribute name="width">100%</xsl:attribute>
				<xsl:value-of select="@value"/>
				</xsl:element>
			</xsl:element>
		</xsl:for-each>
		</xsl:for-each>

	
	</xsl:element>
	</xsl:template>

<xsl:template match="table">
	
</xsl:template>

<xsl:template name="INPUTREPORT" >
	<xsl:element name="div">
		<xsl:attribute name="align"><xsl:value-of select="@align"/></xsl:attribute>
		<xsl:value-of select="@value" disable-output-escaping="yes"/>
	</xsl:element>
	
	
	</xsl:template>

<xsl:template name="TextAreaREPORT">
		<xsl:element name="div">
		<xsl:attribute name="align"><xsl:value-of select="@align"/></xsl:attribute>
		<xsl:value-of select="@value" disable-output-escaping="yes"/>
	</xsl:element>
	</xsl:template>



<xsl:template name="SelectREPORT">
	<xsl:element name="div">
		<xsl:attribute name="align"><xsl:value-of select="@align"/></xsl:attribute>
		<xsl:variable name="value"><xsl:value-of select="@value"/></xsl:variable>
		<xsl:for-each select="options">
			<xsl:for-each select="option">
						<xsl:if test="@value = $value"> 
												<xsl:value-of select="@label"/>
						</xsl:if>
			</xsl:for-each>
		</xsl:for-each>
	</xsl:element>
	</xsl:template>

<xsl:template name="TextREPORT">
	<xsl:element name="div">
	<xsl:attribute name="align"><xsl:value-of select="@align"/></xsl:attribute>
	<xsl:if test="@bold = '1'">
	<xsl:element name="b">
		<xsl:if test="@underline = '1'">
		<xsl:element name="u">
			<xsl:value-of select="@value" disable-output-escaping="yes"/>
		</xsl:element>
		</xsl:if>
		<xsl:if test="@underline = '0'">
			<xsl:value-of select="@value" disable-output-escaping="yes"/>
		</xsl:if>
		
	</xsl:element>
	</xsl:if>
	<xsl:if test="@bold = '0'">
		<xsl:if test="@underline = '1'">
			<xsl:element name="u">
				<xsl:value-of select="@value" disable-output-escaping="yes"/>
			</xsl:element>
		</xsl:if>
		<xsl:if test="@underline = '0'">
				<xsl:value-of select="@value" disable-output-escaping="yes"/>
		</xsl:if>
	</xsl:if>
	</xsl:element>
	</xsl:template>

<xsl:template name="buttonReport">

	<xsl:for-each select="investigations">
			<xsl:element name="div">
				<xsl:attribute name="id">divId_<xsl:value-of select="@name"/></xsl:attribute>
					<xsl:for-each select="investigation">
					<fieldset>
						<xsl:value-of select="text()" disable-output-escaping="yes"/>
					</fieldset>
					</xsl:for-each>
			</xsl:element>
		</xsl:for-each>
		<xsl:for-each select="siteDiagnosisDetail">
			<xsl:element name="div">
				<xsl:attribute name="id">divId_<xsl:value-of select="@name"/></xsl:attribute>
				<xsl:element name="table">
				<xsl:attribute name="width">100%</xsl:attribute>
					<xsl:for-each select="site">
						<xsl:element name="tr">
								<xsl:element name="td">
									<xsl:attribute name="width">30%</xsl:attribute>
									<xsl:value-of select="@name"/>(<xsl:value-of select="@code" disable-output-escaping="no"/>)
								</xsl:element>
								<xsl:element name="td">
								<xsl:attribute name="width">70%</xsl:attribute>
								<xsl:for-each select="diagnosis">
									<xsl:value-of select="@name"/>(<xsl:value-of select="@code" disable-output-escaping="no"/>),
								</xsl:for-each>
								</xsl:element>
						</xsl:element>
					</xsl:for-each>
					</xsl:element>
			</xsl:element>
		</xsl:for-each>
	</xsl:template>

<xsl:template name="imagesectionReport" >
	<xsl:param name="workorderNo"></xsl:param>
	<xsl:element name="input">
			<xsl:if test="@idC!=''">
				<xsl:attribute name="Type">
					<xsl:value-of select="@idC"/>
				</xsl:attribute>
			</xsl:if>
			<xsl:if test="@name!=''">
			<xsl:attribute name="name">
				<xsl:value-of select="@name"/>#<xsl:value-of select="$workorderNo"/>
			</xsl:attribute>
			</xsl:if>
			<xsl:attribute name="onFocus">callOnFocus(this)</xsl:attribute>
			<xsl:if test="@value!=''">
			<xsl:attribute name="value">
				<xsl:value-of select="@value"/>
			</xsl:attribute>
			</xsl:if>
			<xsl:attribute name="style">background-image: url('/AHIMS/hisglobal/images/blankButton.png');width:155px;height:25px;font-size:2px;font-weight:bold;text-align: center;</xsl:attribute>
			<xsl:if test="@callUrl!='/AHIMS/investigation//transaction/searchFunctionality.cnt'">
			<xsl:attribute name="onclick">return openImageUtility('<xsl:value-of select="@callUrl"/>',this);</xsl:attribute>
			</xsl:if>
			<xsl:if test="@callUrl!='/AHIMS/investigation//transaction/radiologicalFindings.cnt'">
			<xsl:attribute name="onclick">return openImageUtility('<xsl:value-of select="@callUrl"/>',this);</xsl:attribute>
			</xsl:if>
		</xsl:element>
		<xsl:for-each select="investigations">
			<xsl:element name="div">
				<xsl:attribute name="id">divId_<xsl:value-of select="@name"/></xsl:attribute>
					<xsl:for-each select="investigation">
						<xsl:value-of select="text()" disable-output-escaping="yes"/>
					</xsl:for-each>
			</xsl:element>
		</xsl:for-each>
	</xsl:template>
	
<xsl:template name="createrequisitionsection" >
<xsl:param name="elementCode"></xsl:param>
<xsl:param name="listIndex"></xsl:param>
<xsl:param name="listgroupIndex"></xsl:param>
<xsl:param name="multisessionid"></xsl:param>
<xsl:element name="table">
		<xsl:element name="tr">
				<xsl:for-each select="images">
					<xsl:for-each select="imageDetails">
						<xsl:for-each select="imageswithactualaddress">
							<xsl:for-each select="image">
							<xsl:element name="td">
								<xsl:attribute name="align">center</xsl:attribute>	
								<xsl:variable name="srcStr"><xsl:value-of select="@readingservlet"/>&amp;listgroupIndex=<xsl:value-of select="$workOrderGroupIndex"/>&amp;index=<xsl:value-of select="@index"/>&amp;elementCode=<xsl:value-of select="$elementCode"/>&amp;processid=2&amp;listIndex=<xsl:value-of select="$listIndex"/></xsl:variable>
								<xsl:variable name="srcStr1"><xsl:value-of select="$srcStr" disable-output-escaping="no"/></xsl:variable>
									<xsl:element name="img"> 
									<xsl:attribute name="src" >
									<xsl:call-template name="string-replace-all"><xsl:with-param name="text" select="$srcStr1" /><xsl:with-param name="replace" select="'#'" /><xsl:with-param name="by" select="'_'" /></xsl:call-template>
 									</xsl:attribute>
									<xsl:attribute name="onclick" >window.open(this.src,'width=400,height=400')</xsl:attribute>
									</xsl:element>									
							</xsl:element>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>	
				</xsl:for-each>
		</xsl:element>
</xsl:element>
</xsl:template>

<xsl:template name="string-replace-all">
    <xsl:param name="text" />
    <xsl:param name="replace" />
    <xsl:param name="by" />
    <xsl:choose>
      <xsl:when test="contains($text, $replace)">
        <xsl:value-of select="substring-before($text,$replace)" />
        <xsl:value-of select="$by" />
        <xsl:call-template name="string-replace-all">
          <xsl:with-param name="text"
          select="substring-after($text,$replace)" />
          <xsl:with-param name="replace" select="$replace" />
          <xsl:with-param name="by" select="$by" />
        </xsl:call-template>
      </xsl:when>
      <xsl:otherwise>
        <xsl:value-of select="$text" />
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>



<!-- image section -->

</xsl:stylesheet>
<!-- Stylus Studio meta-information - (c) 2004-2007. Progress Software Corporation. All rights reserved.
<metaInformation>
<scenarios ><scenario default="yes" name="Scenario1" userelativepaths="yes" externalpreview="no" url="..\sample1.xml" htmlbaseurl="" outputurl="" processortype="saxon8" useresolver="yes" profilemode="0" profiledepth="" profilelength="" urlprofilexml="" commandline="" additionalpath="" additionalclasspath="" postprocessortype="none" postprocesscommandline="" postprocessadditionalpath="" postprocessgeneratedext="" validateoutput="no" validator="internal" customvalidator="" ><advancedProp name="sInitialMode" value=""/><advancedProp name="bXsltOneIsOkay" value="true"/><advancedProp name="bSchemaAware" value="true"/><advancedProp name="bXml11" value="false"/><advancedProp name="iValidation" value="0"/><advancedProp name="bExtensions" value="true"/><advancedProp name="iWhitespace" value="0"/><advancedProp name="sInitialTemplate" value=""/><advancedProp name="bTinyTree" value="true"/><advancedProp name="bWarnings" value="true"/><advancedProp name="bUseDTD" value="false"/><advancedProp name="iErrorHandling" value="fatal"/></scenario></scenarios><MapperMetaTag><MapperInfo srcSchemaPathIsRelative="yes" srcSchemaInterpretAsXML="no" destSchemaPath="" destSchemaRoot="" destSchemaPathIsRelative="yes" destSchemaInterpretAsXML="no"/><MapperBlockPosition></MapperBlockPosition><TemplateContext></TemplateContext><MapperFilter side="source"></MapperFilter></MapperMetaTag>
</metaInformation>
-->