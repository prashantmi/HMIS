<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >
    <xsl:output method="html" indent="yes"/>
    <xsl:template match="/">
    <xsl:param name="elementsequence">0</xsl:param>
    
    <xsl:for-each select="testtemplate">
        <xsl:for-each select="table">
        <xsl:variable name="tableTypeNo"><xsl:value-of select="@type"/></xsl:variable>
                <xsl:element name="table">
                <xsl:if test="@width and @width!=''"><xsl:attribute name="width"><xsl:value-of select="@width"/></xsl:attribute></xsl:if>
                <xsl:if test="not(@width) or @width=''"><xsl:attribute name="width">100%</xsl:attribute></xsl:if>
                <xsl:attribute name="align"><xsl:if test="@align and @align !='' "><xsl:value-of select="@align"/></xsl:if><xsl:if test="not(@align) or @align ='' ">center</xsl:if></xsl:attribute>
                <xsl:if test="@height!=''"><xsl:attribute name="height"><xsl:value-of select="@height"/></xsl:attribute></xsl:if>
                
                <xsl:attribute name="cellspacing"><xsl:value-of select="@cellspacing"/></xsl:attribute>
                <xsl:attribute name="cellpadding"><xsl:value-of select="@cellpadding"/></xsl:attribute>
                <xsl:attribute name="border"><xsl:value-of select="@border"/></xsl:attribute>
                <xsl:attribute name="bordercolor"><xsl:value-of select="@bordercolor"/></xsl:attribute>
                        <xsl:for-each select="rowDetails"> 
                        <xsl:variable name="rowNumber"><xsl:value-of select="@rowNo"/></xsl:variable>
                            <xsl:element name="tr">
                                <xsl:for-each select="columnDetails">
                                <xsl:variable name="colNumber"><xsl:value-of select="@colNo" /></xsl:variable>
                                    <xsl:element name="td">
                                     <xsl:attribute name="id">TD<xsl:value-of select="$tableTypeNo"/><xsl:value-of select="$rowNumber"/><xsl:value-of select="$colNumber"/></xsl:attribute>
                                    <xsl:attribute name="class"><xsl:value-of select="@class"/></xsl:attribute>
                                    <xsl:attribute name="width"><xsl:value-of select="@width"/></xsl:attribute>
                                    <xsl:attribute name="colspan"><xsl:value-of select="@colspan"/></xsl:attribute>
                                    <xsl:attribute name="style">cursor:pointer</xsl:attribute>
                                    <xsl:attribute name="onClick">fetchColumnElementProperties('<xsl:value-of select="$tableTypeNo"/>','<xsl:value-of select="$rowNumber"/>','<xsl:value-of select="$colNumber"/>')</xsl:attribute>
                                        <xsl:element name="div">
                                                <xsl:attribute name="align"><xsl:value-of select="@align"/></xsl:attribute>
                                                    <xsl:for-each select="table">
                                                    <xsl:element name="table">
                                                    <xsl:attribute name="width">100%</xsl:attribute>
                                                        <xsl:for-each select="tr">
                                                        <xsl:element name="tr">
                                                        <xsl:for-each select="td">
                                                            <xsl:element name="td">
                                                            <xsl:attribute name="width">100%</xsl:attribute>
                                                            
                                                                                                
                                                                <xsl:for-each select="element">
                                                                <xsl:variable  name="id">ELE<xsl:value-of select="$tableTypeNo"/><xsl:value-of select="$rowNumber"/><xsl:value-of select="$colNumber"/></xsl:variable>
                                                                <xsl:element name="table">
                                                                <xsl:attribute name="width">100%</xsl:attribute>
                                                                <xsl:attribute name="id"><xsl:value-of select="$id"/></xsl:attribute>
                                                                
                                                                <tr>

                                                                <td width="100%">
                                                                <xsl:element name="div"><xsl:attribute name="align"><xsl:if test="@align and @align != ''"><xsl:value-of select="@align"/></xsl:if><xsl:if test="@align = ''">left</xsl:if><xsl:if test="not(@align)">left</xsl:if></xsl:attribute>
                                                                <xsl:element name="font">
                                                                        <xsl:attribute name="size"><xsl:if test="@fontsize and @fontsize!=''"><xsl:value-of select="@fontsize"/></xsl:if><xsl:if test="not(@fontsize)">1</xsl:if><xsl:if test="@fontsize=''">1</xsl:if></xsl:attribute>
                                                                        <xsl:attribute name="color"><xsl:if test="@fontcolor and @fontcolor!='' "><xsl:value-of select="@fontcolor"/></xsl:if><xsl:if test="not(@fontcolor)">black</xsl:if><xsl:if test="@fontcolor=''">black</xsl:if></xsl:attribute>
                                                                        <xsl:attribute name="id">TD<xsl:value-of select="$tableTypeNo"/><xsl:value-of select="$rowNumber"/><xsl:value-of select="$colNumber"/></xsl:attribute>
                                                                    <xsl:if test="@idC = 'input'">
                                                                        <xsl:call-template name="INPUT" >
                                                                        <xsl:with-param name="tavIndex" select="$elementsequence + 1"/>
                                                                        <xsl:with-param name="typeOfElement" select="@typeOfElement"/>
                                                                        </xsl:call-template>
                                                                    </xsl:if>
                                                                    <xsl:if test="@idC = 'textArea'">
                                                                        <xsl:call-template name="TextArea">
                                                                        <xsl:with-param name="tavIndex"  select="$elementsequence + 1"/>
                                                                        <xsl:with-param name="typeOfElement" select="@typeOfElement"/>

                                                                        </xsl:call-template>

                                                                    </xsl:if>
                                                                    <xsl:if test="@idC = 'Select'">
                                                                        <xsl:call-template name="Select">
                                                                        <xsl:with-param name="tavIndex"><xsl:value-of select="$elementsequence+1"/></xsl:with-param>
                                                                        <xsl:with-param name="typeOfElement" select="@typeOfElement"/>
                                                                        </xsl:call-template>
                                                                    </xsl:if>
                                                                    <xsl:if test="@idC = 'label'">
                                                                        <xsl:call-template name="Text"/>
                                                                    </xsl:if>
                                                                    <xsl:if test="@idC = 'range'">
                                                                        <xsl:call-template name="rangetemplate"/>
                                                                    </xsl:if>
                                                                    <xsl:if test="@idC = 'voValue'">
                                                                        <xsl:call-template name="voValue">
                                                                            <xsl:with-param name="tavIndex"><xsl:value-of select="$elementsequence+1"/></xsl:with-param>

                                                                        </xsl:call-template>
                                                                    </xsl:if>
                                                                    <xsl:for-each select="rangetag">
                                                                        <xsl:element name="b">
                                                                            <xsl:value-of select="@rangefrom"/> <xsl:value-of select="@rangefromunit"/> - <xsl:value-of select="@rangeto"/><xsl:value-of select="@rangefromunit"/>
                                                                        </xsl:element>
                                                                    </xsl:for-each>
                                                                    <xsl:if test="@idC = 'queryValue'">
                                                                        <xsl:call-template name="queryValue"/>
                                                                </xsl:if>
                                                                <xsl:if test="@idC = 'hr'">
                                                                        <xsl:call-template name="HR"/>
                                                                </xsl:if>
                                                                    <xsl:if test="@idC = 'button'">
                                                                    <xsl:attribute name="id"><xsl:value-of select="@name"/></xsl:attribute>
                                                                    
                                                                    <xsl:call-template name="button"><xsl:with-param name="typeOfElement" select="@typeOfElement"/>
                                                                    </xsl:call-template>
                                                                </xsl:if>
                                                                </xsl:element>
                                                            </xsl:element>
                                                            </td>
                                                                </tr>
                                                                    </xsl:element>

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
    <xsl:param name="typeOfElement"></xsl:param>
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
            <xsl:if test="@name!=''">
            <xsl:attribute name="id">
                <xsl:value-of select="$typeOfElement"/><xsl:value-of select="@labCode"/><xsl:value-of select="@name"/>
            </xsl:attribute>
            </xsl:if>

            <xsl:attribute name="onFocus">callOnFocus(this)</xsl:attribute>
            <xsl:if test="@value!=''">
            <xsl:attribute name="value">
                <xsl:value-of select="@value"/>
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
        <xsl:param name="typeOfElement"></xsl:param>
        <xsl:element name="textarea">
            <xsl:attribute name="tabIndex">
            <xsl:value-of select="$tavIndex"/>
            </xsl:attribute>
            <xsl:attribute name="onFocus">callOnFocus(this)</xsl:attribute>
            <xsl:if test="@name!=''">
            <xsl:attribute name="name">
                <xsl:value-of select="@name"/>
            </xsl:attribute>
            <xsl:attribute name="id">
               <xsl:value-of select="$typeOfElement"/><xsl:value-of select="@labCode"/><xsl:value-of select="@name"/>
            </xsl:attribute>
            </xsl:if>
            <xsl:if test="@rows!=''">
            <xsl:attribute name="rows">
                <xsl:value-of select="@rows"/>
            </xsl:attribute>
            </xsl:if>
            <xsl:if test="@cols!=''">
            <xsl:attribute name="cols">
                <xsl:value-of select="@cols"/>
            </xsl:attribute>
            </xsl:if>
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
            <xsl:if test="@maxlength!=''">    
            <xsl:attribute name="maxlength">
                <xsl:value-of select="@maxlength"/>
            </xsl:attribute>
            </xsl:if>

            
            <xsl:value-of select="@value"/>
            <xsl:if test="@eventName = '0'">
            <xsl:attribute name="onKeyPress">
                    <xsl:value-of select="@eventValidationString"/>
            </xsl:attribute>
            </xsl:if>

            <xsl:if test=" @eventName= '0'">
            <xsl:attribute name="{@eventName}">
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
        <xsl:if test="@editor = '1'">
        <xsl:element name="script">
        <xsl:attribute name="language">javascript</xsl:attribute>
        WYSIWYG.attach('<xsl:value-of select="@name"/>',full);
        </xsl:element>
        </xsl:if>
    </xsl:template>
    <xsl:template name="Select">
    <xsl:param name="tavIndex" />
    <xsl:param name="typeOfElement"></xsl:param>
        <xsl:element name="select">
        <xsl:attribute name="tabIndex">
            <xsl:value-of select="tavIndex"/>
        </xsl:attribute>

            <xsl:if test="@name!=''">
            <xsl:attribute name="name">
                <xsl:value-of select="@name"/>
            </xsl:attribute>
            </xsl:if>
            <xsl:if test="@name!=''">
            <xsl:attribute name="id">
               <xsl:value-of select="$typeOfElement"/><xsl:value-of select="@labCode"/><xsl:value-of select="@name"/>
            </xsl:attribute>
            </xsl:if>
            
            <xsl:variable name="val" select="@value"/>
            <xsl:attribute name="value">
            <xsl:value-of select="$val"/>
            </xsl:attribute>
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
            <xsl:if test="$val != @value">
                <xsl:element name="option"><xsl:attribute name="value"><xsl:value-of select="@value"/></xsl:attribute><xsl:value-of select="@label"/></xsl:element>
            </xsl:if>
            <xsl:if test="$val = @value">
                <xsl:element name="option"><xsl:attribute name="selected">selected</xsl:attribute><xsl:attribute name="value"><xsl:value-of select="@value"/></xsl:attribute><xsl:value-of select="@label"/></xsl:element>
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
    

</xsl:template>
<xsl:template name="rangetemplate">
    RTO Ele: <xsl:value-of select="@roe"/> T:<xsl:value-of select="@tableNo"/> R:<xsl:value-of select="@rowNo"/>C:<xsl:value-of select="@colNo"/>

</xsl:template>
<xsl:template match="table">
    
</xsl:template>


<xsl:template name="HR" >
    <xsl:element name="hr">
        <xsl:attribute name="size"><xsl:value-of select="@size"/></xsl:attribute>
        <xsl:attribute name="color"><xsl:value-of select="@color"/></xsl:attribute>
    </xsl:element>
</xsl:template>



<xsl:template name="button" >
    <xsl:param name="typeOfElement"></xsl:param>
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
            <xsl:if test="@name!=''">
            <xsl:attribute name="id">
                <xsl:value-of select="$typeOfElement"/><xsl:value-of select="@labCode"/><xsl:value-of select="@name"/>
            </xsl:attribute>
            </xsl:if>

            <xsl:attribute name="onFocus">callOnFocus(this)</xsl:attribute>
            <xsl:if test="@value!=''">
            <xsl:attribute name="value">
                <xsl:value-of select="@value"/>
            </xsl:attribute>
            </xsl:if>
            
            <xsl:attribute name="onclick">return openPopupResultEntry('<xsl:value-of select="@callUrl"/>',this);</xsl:attribute>
            
            


        </xsl:element>
    </xsl:template>
<xsl:template name="voValue">
    <xsl:param name="tavIndex" ></xsl:param>
        
            <xsl:value-of select="@name"/>
        
        
    </xsl:template>

<xsl:template name="queryValue" >
<table width="100%"><tr><td width="100%">Results of Query Value:<xsl:value-of select="@name"/>goes here</td></tr></table>
    

    </xsl:template>

</xsl:stylesheet>