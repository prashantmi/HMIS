<?xml version="1.0" encoding="UTF-8"?><xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output indent="yes" method="html"/>
<xsl:param name="workOrder"/>
<xsl:param name="workOrderIndex"/>
<xsl:param name="multisessionid"/>
<xsl:param name="normalValues"/>
<xsl:param name="sampleCode"/>
<xsl:param name="testName"/>
<xsl:param name="sessionNo"/>
<xsl:template match="/">
<xsl:element name="table"><xsl:attribute name="width">100%</xsl:attribute> 
<xsl:for-each select="testtemplate">
            <xsl:for-each select="table[@type!='header'][@type!='footer']">
            <xsl:element name="tr"><xsl:element name="td"><xsl:attribute name="width">100%</xsl:attribute> <xsl:attribute name="valign">top</xsl:attribute>
                <xsl:if test="rowDetails"> 
                <xsl:element name="table">
                <xsl:attribute name="width">100%</xsl:attribute>
                <xsl:attribute name="border">0</xsl:attribute>
                <xsl:attribute name="align">center</xsl:attribute>
                <xsl:attribute name="cellspacing">0</xsl:attribute>
                <xsl:attribute name="cellpadding">0</xsl:attribute>
                    <xsl:element name="tr">
                        <xsl:element name="td">
                        <xsl:attribute name="width">100%</xsl:attribute>
                        <xsl:element name="table">
                        <xsl:attribute name="width">100%</xsl:attribute>
                        <xsl:attribute name="height">100%</xsl:attribute>
                        <xsl:attribute name="cellspacing">0</xsl:attribute>
                        <xsl:attribute name="border"><xsl:value-of select="@border"/></xsl:attribute>
                        <xsl:attribute name="cellpadding">0</xsl:attribute>
                        <xsl:attribute name="align">center</xsl:attribute>
                        <xsl:for-each select="rowDetails"> 
                            <xsl:element name="tr">
                                <xsl:for-each select="columnDetails">
                                    <xsl:element name="td">
                                    <xsl:attribute name="width"><xsl:value-of select="@width"/></xsl:attribute>
                                    <xsl:attribute name="height">100%</xsl:attribute>
                                    <xsl:if test="@colspan">
                                    <xsl:attribute name="colspan"><xsl:value-of select="@colspan"/></xsl:attribute>
                                    </xsl:if>
                                    <!--xsl:attribute name="border">1</xsl:attribute-->
                                    <xsl:element name="div"> 
                                                <xsl:attribute name="align">center</xsl:attribute>
                                                <xsl:attribute name="style">width:100%</xsl:attribute>
                                                <xsl:for-each select="table">
                                                    <xsl:element name="table">
                                                        <xsl:attribute name="width">100%</xsl:attribute>
                                                         <xsl:attribute name="border">0</xsl:attribute>
                                                            <xsl:for-each select="tr">
                                                                <xsl:element name="tr">
                                                                        <xsl:for-each select="td">
                                                                        <xsl:element name="td">
                                                                        <xsl:attribute name="width">100%</xsl:attribute>
                                                                                <xsl:for-each select="element">
                                                                                    <xsl:if test="@idC ='hr'">
                                                                                    <xsl:attribute name="height">10</xsl:attribute>
                                                                                    <xsl:attribute name="bgcolor">black</xsl:attribute>
                                                                                    <img name="transparent" src="transparent.gif"/>
                                                                                    </xsl:if>
                                                                                        <xsl:if test="@idC != 'hr'">    
                                                                                            <xsl:element name="div"><xsl:attribute name="align"><xsl:if test="@align and @align != ''"><xsl:value-of select="@align"/></xsl:if><xsl:if test="@align = ''">left</xsl:if><xsl:if test="not(@align)">left</xsl:if></xsl:attribute>
                                                                                                <xsl:element name="font">
                                                                                                <xsl:attribute name="size"><xsl:if test="@fontsize and @fontsize!=''"><xsl:value-of select="@fontsize"/></xsl:if><xsl:if test="not(@fontsize)">1</xsl:if><xsl:if test="@fontsize=''">1</xsl:if></xsl:attribute>
                                                                                                <xsl:attribute name="color"><xsl:if test="@fontcolor and @fontcolor!='' "><xsl:value-of select="@fontcolor"/></xsl:if><xsl:if test="not(@fontcolor)">black</xsl:if><xsl:if test="@fontcolor=''">black</xsl:if></xsl:attribute>
                                                                                            
                                                                                                            
                                                                                                        <xsl:if test="@idC = 'input'">
                                                                                                            <xsl:call-template name="Text"/>
                                                                                                        </xsl:if>
                                                                                                        <xsl:if test="@idC = ''">
                                                                                                            <font> </font>
                                                                                                        </xsl:if>
                                                                                                        <xsl:if test="@idC = 'textArea'">
                                                                                                            <xsl:call-template name="TextArea"/>
                                                                                                        </xsl:if>
                                                                                                        <xsl:if test="@idC = 'Select'">
                                                                                                            <xsl:call-template name="selectValue"><xsl:with-param name="sampleCode" select="$sampleCode"/></xsl:call-template>
                                                                                                        </xsl:if>
                                                                                                        <xsl:if test="@idC = 'label'">
                                                                                                            <xsl:call-template name="Label"/>
                                                                                                        </xsl:if>
                                                                                                        <xsl:if test="@idC = 'button'">
                                                                                                            <xsl:attribute name="id">td#<xsl:value-of select="@name"/></xsl:attribute>
                                                                                                            <xsl:call-template name="buttonReport"/>
                                                                                                        </xsl:if>
                                                                                                        <xsl:if test="@idC = 'voValue'">
                                                                                                        <xsl:call-template name="voValue"/>
                                                                                                        </xsl:if>
                                                                                                        <xsl:if test="@idC = 'queryValue'">
                                                                                                            <xsl:call-template name="queryValue"/>
                                                                                                        </xsl:if>
                                                                                                        <xsl:if test="@idC = 'range'">
                                                                                                            <xsl:call-template name="rangetemplate"/>
                                                                                                        </xsl:if>
                                                                                                </xsl:element>
                                                                                            </xsl:element>
                                                                                        </xsl:if>
                                                                                            
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
            
            
            
            </xsl:element>
            </xsl:element>
            </xsl:element>
            </xsl:if>
            





</xsl:element>
</xsl:element>
            </xsl:for-each>
            
</xsl:for-each>



</xsl:element>    

</xsl:template>
    <xsl:template name="Text">
    
    <xsl:variable name="v" select="@value"/> 
    <xsl:variable name="check">No</xsl:variable>
    
    
    <xsl:if test="rangetag">
    <xsl:for-each select="rangetag">
    <xsl:choose>
                <xsl:when test="not(number($v))">
                <xsl:value-of select="$v"/>
                </xsl:when>
                <xsl:when test="number($v) &gt;= number(@rangefrom) and number($v) &lt;= number(@rangeto)">
                <xsl:value-of select="$v"/>
                </xsl:when>
                <xsl:when test="number($v) &lt; number(@rangefrom) or number($v) &gt; number(@rangeto)">
                <xsl:element name="b">
                <xsl:value-of select="$v" disable-output-escaping="no"/>
                </xsl:element>
                </xsl:when>
            <xsl:otherwise>
                <xsl:if test="not(current()/rangetag)">
                    <xsl:choose>
                        <xsl:when test="@bold='1'">
                            <b><xsl:value-of select="$v"/></b>
                        </xsl:when>
                    <xsl:otherwise><xsl:value-of select="$v"/></xsl:otherwise>
                    </xsl:choose>
                </xsl:if>
            </xsl:otherwise>
            </xsl:choose>

    </xsl:for-each>
    </xsl:if>

    <xsl:if test="not(rangetag)">
    <xsl:choose>
        <xsl:when test="@bold='1'">
            <b><xsl:value-of disable-output-escaping="no" select="$v"/></b>
        </xsl:when>
        <xsl:otherwise><xsl:value-of disable-output-escaping="no" select="$v"/></xsl:otherwise>
        </xsl:choose>
    </xsl:if>
    
    
    
    

    
</xsl:template>
<xsl:template name="TextArea">
   
    
    <xsl:variable name="v" select="@value"/> 
    <xsl:variable name="check">No</xsl:variable>
    
    
    <xsl:if test="rangetag">
    <xsl:for-each select="rangetag">
    <xsl:if  test="number($v)">
    <xsl:if test="number($v) &gt;= number(@rangefrom) and number($v) &lt;= number(@rangeto)">
            <xsl:value-of select="$v"/>
    </xsl:if>
    <xsl:if test="number($v) &lt; number(@rangefrom) or number($v) &gt; number(@rangeto)">
    <xsl:element name="b">
        <xsl:value-of select="$v"/>
    </xsl:element>
    </xsl:if>
    
    <xsl:if test="number($v) &gt;= number(@rangefrom) and number($v) = ''">
            <xsl:value-of select="$v"/>
    </xsl:if>  
    <xsl:if  test="not(number(@rangefrom)) or not(number(@rangeto))">
      <xsl:value-of select="$v" disable-output-escaping="yes"/>
     </xsl:if>    
    </xsl:if>
    
     
    <xsl:if  test="not(number($v))">
      <xsl:value-of select="$v" disable-output-escaping="yes"/>
     </xsl:if>

    </xsl:for-each>
    </xsl:if>

    <xsl:if test="not(rangetag)">
    <xsl:choose>
        <xsl:when test="@bold='1'">
            <b><xsl:value-of disable-output-escaping="yes" select="$v"/></b>
        </xsl:when>
        <xsl:otherwise><xsl:value-of disable-output-escaping="yes" select="$v"/></xsl:otherwise>
        </xsl:choose>
    </xsl:if>
    
 
    
</xsl:template>
<xsl:template name="Label">
    
    <xsl:variable name="v" select="@value"/> 
    <xsl:variable name="check">No</xsl:variable>
    
    
    <xsl:if test="rangetag">
    <xsl:for-each select="rangetag">
    <xsl:choose>
                <xsl:when test="not(number($v))">
                <xsl:value-of select="$v"/>
                </xsl:when>
                <xsl:when test="number($v) &gt;= number(@rangefrom) and number($v) &lt;= number(@rangeto)">
                <xsl:value-of select="$v"/>
                </xsl:when>
                <xsl:when test="number($v) &lt; number(@rangefrom) or number($v) &gt; number(@rangeto)">
                <xsl:element name="b">
                <xsl:value-of select="$v" disable-output-escaping="yes"/>
                </xsl:element>
                </xsl:when>
            <xsl:otherwise>
                <xsl:if test="not(current()/rangetag)">
                    <xsl:choose>
                        <xsl:when test="@bold='1'">
                            <b><xsl:value-of select="$v"/></b>
                        </xsl:when>
                    <xsl:otherwise><xsl:value-of select="$v"/></xsl:otherwise>
                    </xsl:choose>
                </xsl:if>
            </xsl:otherwise>
            </xsl:choose>

    </xsl:for-each>
    </xsl:if>

    <xsl:if test="not(rangetag)">
    <xsl:choose>
        <xsl:when test="@bold='1'">
            <b><xsl:value-of disable-output-escaping="yes" select="$v"/></b>
        </xsl:when>
        <xsl:otherwise><xsl:value-of disable-output-escaping="yes" select="$v"/></xsl:otherwise>
        </xsl:choose>
    </xsl:if>
    
    
    
    

    
</xsl:template>
<xsl:template name="rangetemplate">
    
    <xsl:variable name="elementName" select="@roe"/> 
    <xsl:variable name="elementRowNo" select="@rowNo"/>
    <xsl:variable name="elementColNo" select="@colNo"/>
    <xsl:element name="div"> 
    <xsl:attribute name="align"><xsl:value-of select="@align"/></xsl:attribute>
        <xsl:element name="font"> 
            <xsl:attribute name="size"><xsl:value-of select="@fontsize"/></xsl:attribute>
            <xsl:attribute name="color"><xsl:value-of select="@fontcolor"/></xsl:attribute>
                <xsl:for-each select="ancestor::testtemplate/table/rowDetails[@rowNo=$elementRowNo]/columnDetails[@colNo=$elementColNo]/table/tr/td/element[@name=$elementName]/rangetag">
                     <xsl:value-of select="@rangefrom"/> - <xsl:value-of select="@rangeto"/>
                   </xsl:for-each>
        </xsl:element>
    </xsl:element>

</xsl:template>

<xsl:template match="tr">
    
</xsl:template>

<xsl:template match="test">
    
</xsl:template>

<xsl:template name="designRows">
<xsl:param name="isAlreadyAccepted"/>
<xsl:param name="maxColumns"/>
<!-- Start of if 1 -->
    <xsl:for-each select="child::columnDetails[1]">
        <xsl:call-template name="columnDesignerTD">
            <xsl:with-param name="isAlreadyAccepted">1</xsl:with-param>
            <xsl:with-param name="maxColumns"><xsl:value-of select="$maxColumns"/></xsl:with-param>
        </xsl:call-template>
    </xsl:for-each>
                            
                            
                            
</xsl:template>
<xsl:template name="columnDesignerTD">
<xsl:param name="isAlreadyAccepted"/>
<xsl:param name="maxColumns"/>
<!-- <xsl:value-of select="$isAlreadyAccepted"/>a<xsl:value-of select="current()/table/tr/td/element/@idC"/> -->
<xsl:choose>
<xsl:when test="($isAlreadyAccepted = '0') and (current()/table/tr/td/element/@idC = '')">
<!-- Do nothing --> 
</xsl:when>
<xsl:otherwise>
<!--Process the Current Element -->
<xsl:choose> 
<xsl:when test="current()/table/tr/td/element/@idC = ''">
<xsl:element name="td"><xsl:attribute name="colspan"><xsl:if test="@colspan != null"><xsl:value-of select="@colspan"/></xsl:if><xsl:if test="@colspan = null">1</xsl:if></xsl:attribute></xsl:element>
</xsl:when>
<xsl:when test="current()/table/tr/td/element/@idC = 'label'">
<xsl:element name="td"><xsl:attribute name="colspan"><xsl:if test="@colspan != null"><xsl:value-of select="@colspan"/></xsl:if><xsl:if test="@colspan = null">1</xsl:if></xsl:attribute><xsl:value-of select="current()/table/tr/td/element/@value"/></xsl:element>
</xsl:when>
<xsl:when test="current()/table/tr/td/element/@idC = 'input'">
<xsl:element name="td">
    <xsl:choose>
    <xsl:when test="$maxColumns = @colNo">
    <xsl:attribute name="colspan">1</xsl:attribute>
    </xsl:when>
    <xsl:otherwise>
    <xsl:attribute name="colspan">1</xsl:attribute>
    </xsl:otherwise>
    </xsl:choose>
    
<xsl:attribute name="align">left</xsl:attribute>
<xsl:attribute name="colspan"><xsl:value-of select="@colspan"/></xsl:attribute>
<xsl:attribute name="width">400</xsl:attribute>
<xsl:for-each select="current()/table/tr/td/element">
<xsl:call-template name="Text"/>
</xsl:for-each>
    </xsl:element>

</xsl:when>
<xsl:when test="current()/table/tr/td/element/@idC = 'Select'">
<xsl:element name="td">
<xsl:choose>
    <xsl:when test="$maxColumns = @colNo">
    <xsl:attribute name="colspan">1</xsl:attribute>
    </xsl:when>
    <xsl:otherwise>
    <xsl:attribute name="colspan"><xsl:value-of select="number($maxColumns)-number(current()/@colNo)+1"/></xsl:attribute>
    </xsl:otherwise>
    </xsl:choose>
<xsl:attribute name="align">right</xsl:attribute>
<xsl:for-each select="current()/table/tr/td/element">
<xsl:call-template name="selectValue"><xsl:with-param name="sampleCode" select="$sampleCode"/></xsl:call-template>
</xsl:for-each>

</xsl:element>
</xsl:when>






<xsl:when test="current()/table/tr/td/element/@idC = 'textArea'">
<xsl:element name="td">
<xsl:choose>
    <xsl:when test="$maxColumns = @colNo">
    <xsl:attribute name="colspan">1</xsl:attribute>
    </xsl:when>
    <xsl:otherwise>
    <xsl:attribute name="colspan"><xsl:value-of select="number($maxColumns)-number(current()/@colNo)+1"/></xsl:attribute>
    </xsl:otherwise>
    </xsl:choose>
<xsl:attribute name="align">left</xsl:attribute><xsl:value-of disable-output-escaping="yes" select="current()/table/tr/td/element/@value"/>
</xsl:element>
</xsl:when>
<xsl:when test="current()/table/tr/td/element/@idC = 'button'">
<xsl:element name="td">
<xsl:choose>
    <xsl:when test="$maxColumns = @colNo">
    <xsl:attribute name="colspan">1</xsl:attribute>
    </xsl:when>
    <xsl:otherwise>
    <xsl:attribute name="colspan"><xsl:value-of select="number($maxColumns)-number(current()/@colNo)+1"/></xsl:attribute>
    </xsl:otherwise>
    </xsl:choose>
<xsl:attribute name="align">left</xsl:attribute>
<!--<xsl:call-template name="buttonReportNode"/>-->


<xsl:for-each select="current()/table/tr/td/element/investigations">
            <xsl:element name="div">
                <xsl:attribute name="id">divId_<xsl:value-of select="@name"/></xsl:attribute>
                    <xsl:for-each select="investigation">
                        <xsl:value-of disable-output-escaping="yes" select="text()"/>
                    </xsl:for-each>
            </xsl:element>
        </xsl:for-each>
        <xsl:for-each select="current()/table/tr/td/element/sitediagnosisdetail">
        <font size="2">
            <xsl:element name="div">
                <xsl:attribute name="id">divId_<xsl:value-of select="@name"/></xsl:attribute>
                <xsl:element name="table">
                <xsl:attribute name="width">100%</xsl:attribute>
                    <xsl:for-each select="site">
                        <xsl:element name="tr">
                                <xsl:element name="td">
                                <xsl:attribute name="width">30%</xsl:attribute>
                                <xsl:attribute name="align">left</xsl:attribute>
                                        <xsl:value-of select="@name"/>(<xsl:value-of disable-output-escaping="no" select="@code"/>)
                                </xsl:element>
                                <xsl:element name="td">
                                <xsl:attribute name="width">70%</xsl:attribute>
                                <xsl:attribute name="align">left</xsl:attribute>
                                <xsl:for-each select="diagnosis">
                                        
                                        <xsl:value-of select="@name"/>(<xsl:value-of disable-output-escaping="no" select="@code"/>),
                                </xsl:for-each>
                                </xsl:element>
                        </xsl:element>
                    </xsl:for-each>
                    </xsl:element>
            </xsl:element>
        </font>    
        </xsl:for-each>
        
        <xsl:for-each select="current()/table/tr/td/element/siteDiagnosisDetail">
        <font size="2">
            <xsl:element name="div">
                <xsl:attribute name="id">divId_<xsl:value-of select="@name"/></xsl:attribute>
                <xsl:element name="table">
                <xsl:attribute name="width">100%</xsl:attribute>
                    <xsl:for-each select="site">
                        <xsl:element name="tr">
                                <xsl:element name="td">
                                <xsl:attribute name="width">30%</xsl:attribute>
                                <xsl:attribute name="align">left</xsl:attribute>
                                        <xsl:value-of select="@name"/>(<xsl:value-of disable-output-escaping="no" select="@code"/>)
                                </xsl:element>
                                <xsl:element name="td">
                                <xsl:attribute name="width">70%</xsl:attribute>
                                <xsl:attribute name="align">left</xsl:attribute>
                                <xsl:for-each select="diagnosis">
                                        <xsl:value-of select="@name"/>(<xsl:value-of disable-output-escaping="no" select="@code"/>),
                                </xsl:for-each>
                                </xsl:element>
                        </xsl:element>
                    </xsl:for-each>
                    </xsl:element>
            </xsl:element>
        </font>    
        </xsl:for-each> 


</xsl:element>
</xsl:when>

<xsl:otherwise><td>element rejected</td></xsl:otherwise>
</xsl:choose>

<!-- consider the next element -->
<xsl:choose> 
<xsl:when test="current()/table/tr/td/element/@idC = '' or current()/table/tr/td/element/@idC = 'label' ">
<xsl:if test="current()/following-sibling::columnDetails[1]">
<xsl:for-each select="current()/following-sibling::columnDetails[1]">
<xsl:call-template name="columnDesignerTD">
            <xsl:with-param name="isAlreadyAccepted">1</xsl:with-param>
            <xsl:with-param name="maxColumns"><xsl:value-of select="$maxColumns"/></xsl:with-param>
        </xsl:call-template>
        </xsl:for-each>
</xsl:if>
</xsl:when>
<xsl:otherwise>
<xsl:if test="current()/following-sibling::columnDetails[1]">
<xsl:for-each select="current()/following-sibling::columnDetails[1]">
<xsl:call-template name="columnDesignerTD">
            <xsl:with-param name="isAlreadyAccepted">0</xsl:with-param>
            <xsl:with-param name="maxColumns"><xsl:value-of select="$maxColumns"/></xsl:with-param>
        </xsl:call-template>
        </xsl:for-each>
</xsl:if>
</xsl:otherwise>
</xsl:choose>


</xsl:otherwise>
</xsl:choose>
</xsl:template>









<xsl:template name="columnDesigner">
<xsl:param name="maxColumns"/>
<xsl:variable name="currentColumnNo"><xsl:value-of select="@colNo"/></xsl:variable>
                <xsl:if test="current()/table/tr/td/element">
                    <xsl:element name="td">
                        <xsl:if test="current()/table/tr/td/element/@idC != '' and current()/table/tr/td/element/@idC != 'label'">
                        <xsl:attribute name="colspan"><xsl:value-of select="number($maxColumns)- number($currentColumnNo) + 1"/></xsl:attribute>
                        <xsl:attribute name="width"><xsl:value-of select="(number(100 div number($maxColumns))*(number($maxColumns) - number($currentColumnNo) +1))"/>%</xsl:attribute>
                    </xsl:if>
                    <xsl:if test="current()/table/tr/td/element/@idC = '' or current()/table/tr/td/element/@idC = 'label' ">
                        <xsl:attribute name="width"><xsl:value-of select="@width"/></xsl:attribute>
                    </xsl:if>
                    <xsl:attribute name="border">1</xsl:attribute>
                        <xsl:element name="div"> 
                        <xsl:attribute name="align">right</xsl:attribute>
                        <xsl:for-each select="table">
                            <xsl:element name="table">
                                <xsl:choose>
                                    <xsl:when test="current()/tr/td/element/@idC = '' or current()/tr/td/element/@idC = 'label' ">
                                       <xsl:attribute name="width">100%</xsl:attribute>
                                        </xsl:when>
                                <xsl:otherwise>
                            <xsl:choose>
                            <xsl:when test="number($maxColumns) = number($currentColumnNo)">
                                    <xsl:attribute name="width">100%</xsl:attribute>
                            </xsl:when>
                            <xsl:otherwise>
                             <xsl:attribute name="width"><xsl:value-of select="(number($maxColumns)-(number($maxColumns) - number($currentColumnNo)+1))*(100 div number($maxColumns))"/>%</xsl:attribute>
                            </xsl:otherwise>
                        </xsl:choose>
                    </xsl:otherwise>
                </xsl:choose>
    <xsl:for-each select="tr">
        <xsl:element name="tr">
             <xsl:for-each select="td">
                <xsl:attribute name="align">center</xsl:attribute>
                        <xsl:for-each select="element">
                            <xsl:element name="td">
                                <xsl:choose>
                                      <xsl:when test="current()/rangetag">
                                        <xsl:attribute name="width">50%</xsl:attribute>
                                      </xsl:when>
                                   <xsl:otherwise>
                                      <xsl:attribute name="width">100%</xsl:attribute>
                                  </xsl:otherwise>
                                </xsl:choose>
                                                                        
                                <xsl:if test="@idC = 'input'">
                                            <xsl:call-template name="Text"/>
                                </xsl:if>
                                <xsl:if test="@idC = ''">
                                <font> </font>
                                </xsl:if>
    <xsl:if test="@idC = 'textArea'">
        <xsl:call-template name="Text"/>
    </xsl:if>
    <xsl:if test="@idC = 'Select'">
    <xsl:call-template name="selectValue"/>
        <xsl:call-template name="Text"/>
    </xsl:if>
    <xsl:if test="@idC = 'label'">
            <xsl:call-template name="Text"/>
    </xsl:if>
    </xsl:element>
    <xsl:if test="@idC != 'label' and @idC != '' ">
        <xsl:element name="td">
            <xsl:attribute name="width">50%</xsl:attribute>
        <xsl:element name="div">
            <xsl:attribute name="align">center</xsl:attribute>
        <xsl:if test="current()/rangetag">
                <font size="2"> </font>
        </xsl:if>
        <xsl:for-each select="rangetag">
            <xsl:call-template name="DISPLAYRANGETAG"/>
        </xsl:for-each>
        </xsl:element>
    </xsl:element>
    </xsl:if>
    </xsl:for-each>
    </xsl:for-each>
    </xsl:element>
    </xsl:for-each>
    </xsl:element>
    </xsl:for-each>

    </xsl:element>
    </xsl:element>
    </xsl:if><!--end of if 1 -->
</xsl:template>    
<xsl:template name="selectValue">
<xsl:param name="sampleCode"/> 
<xsl:variable name="selectVal"><xsl:value-of select="@value"/></xsl:variable>
<xsl:choose>
<xsl:when test="@functionName = 'getOrganism'">    
<xsl:value-of select="@value"/>
<xsl:value-of select="document('organism101.xml')/organism/option[@value = $selectVal]/text()"/>
</xsl:when>
<xsl:when test="@functionName = 'getSampleOrganism'">    
<xsl:value-of select="document('sampleorganism101.xml')/sample[@code = $sampleCode]/organism/option[@value = $selectVal]/text()"/>
</xsl:when>
<xsl:otherwise>
<xsl:value-of select="options/option[@value = $selectVal]/@label"/>
</xsl:otherwise>
</xsl:choose>

</xsl:template>
<xsl:template name="DISPLAYRANGETAG">
<font size="2">
<xsl:if test="@rangeto = '' and @rangefrom = ''">--</xsl:if>

<xsl:if test="@rangeto != '' and @rangefrom != ''">
    <xsl:value-of select="@rangefrom"/> <xsl:value-of disable-output-escaping="yes" select="@rangefromunit"/> - <xsl:value-of select="@rangeto"/><xsl:value-of disable-output-escaping="yes" select="@rangefromunit"/>
</xsl:if>

<xsl:if test="@rangefrom = '' and @rangeto != ''">
    &lt;<xsl:value-of select="@rangeto"/><xsl:value-of disable-output-escaping="yes" select="@rangetounit"/>
</xsl:if>

<xsl:if test="@rangeto = '' and @rangefrom != ''">
    &gt; <xsl:value-of select="@rangefrom"/><xsl:value-of disable-output-escaping="yes" select="@rangefromunit"/>
</xsl:if>
</font>
</xsl:template>
<xsl:template name="buttonReport">

<xsl:value-of select="current()/table/tr/td/element/@idC"/>
    <xsl:for-each select="investigations">
            <xsl:element name="div">
                <xsl:attribute name="id">divId_<xsl:value-of select="@name"/></xsl:attribute>
                    <xsl:for-each select="investigation">
                        <xsl:value-of disable-output-escaping="yes" select="text()"/>
                    </xsl:for-each>
            </xsl:element>
        </xsl:for-each>
        <xsl:for-each select="sitediagnosisdetail">
        <font size="2">
            <xsl:element name="div">
                <xsl:attribute name="id">divId_<xsl:value-of select="@name"/></xsl:attribute>
                <xsl:element name="table">
                <xsl:attribute name="width">100%</xsl:attribute>
                    <xsl:for-each select="site">
                        <xsl:element name="tr">
                                <xsl:element name="td">
                                <xsl:attribute name="width">30%</xsl:attribute>
                                <xsl:attribute name="align">left</xsl:attribute>
                                    <xsl:value-of select="@name"/>(<xsl:value-of disable-output-escaping="no" select="@code"/>)
                                </xsl:element>
                                <xsl:element name="td">
                                <xsl:attribute name="width">70%</xsl:attribute>
                                <xsl:attribute name="align">left</xsl:attribute>
                                <xsl:for-each select="diagnosis">
                                    <xsl:value-of select="@name"/>(<xsl:value-of disable-output-escaping="no" select="@code"/>),
                                </xsl:for-each>
                                </xsl:element>
                        </xsl:element>
                    </xsl:for-each>
                    </xsl:element>
            </xsl:element>
            </font>
        </xsl:for-each>
    <xsl:for-each select="siteDiagnosisDetails">
    <font size="2">
            <xsl:element name="div">
                <xsl:attribute name="id">divId_<xsl:value-of select="@name"/></xsl:attribute>
                <xsl:element name="table">
                <xsl:attribute name="width">100%</xsl:attribute>
                <xsl:element name="tr">
                                <xsl:element name="td">
                                <xsl:attribute name="width">30%</xsl:attribute>
                                <xsl:attribute name="align">left</xsl:attribute>
                                <div align="left"><b>Site</b></div>
                                </xsl:element>
                                <xsl:element name="td">
                                <xsl:attribute name="width">70%</xsl:attribute>
                                <xsl:attribute name="align">left</xsl:attribute>
                                <div align="left"><b>Diagnosis</b></div>    
                                </xsl:element>
                        </xsl:element>
                    <xsl:for-each select="site">
                        <xsl:element name="tr">
                                <xsl:element name="td">
                                <xsl:attribute name="width">30%</xsl:attribute>
                                <xsl:attribute name="align">left</xsl:attribute>
                                    <xsl:value-of select="@name"/>(<xsl:value-of select="@code" disable-output-escaping="no"/>)
                                </xsl:element>
                                <xsl:element name="td">
                                <xsl:attribute name="width">70%</xsl:attribute>
                                <xsl:attribute name="align">left</xsl:attribute>
                                <xsl:for-each select="diagnosis">
                                    <xsl:value-of select="@name"/>(<xsl:value-of select="@code" disable-output-escaping="no"/>),
                                </xsl:for-each>
                                </xsl:element>
                        </xsl:element>
                    </xsl:for-each>
                    </xsl:element>
            </xsl:element>
        </font>    
        </xsl:for-each>
    
    <!-- anti microbial test results-->
        
            <xsl:for-each select="antisusceptibility">
            <font size="2">
            <xsl:element name="div">
                
                <table cellpadding="0" cellspacing="0" width="100%"><tr><td width="100%">
                <table width="100%">
                            <tr><td width="34%"><div align="left"><b>Antibiotics / Organism </b></div></td>
                        <xsl:for-each select="rows/organismrow">
                            <xsl:element name="td"><xsl:attribute name="width"><xsl:value-of select="@width"/></xsl:attribute>
                                <div align="left"><b><xsl:value-of select="@value"/></b></div>
                            </xsl:element>
                        </xsl:for-each>                    
                </tr>
                </table>
                <xsl:for-each select="antibioticclasses">
                        <xsl:for-each select="antibioticclass">
                        <table width="100%">
                        <tr><td width="34%"><div align="left"><b><xsl:value-of select="@value"/></b></div></td></tr>
                        <xsl:for-each select="antibiotic">
                        <tr><td width="34%"><div align="left"><b><xsl:value-of select="@value"/></b></div></td>
                            <xsl:for-each select="organism">
                            <xsl:element name="td">
                                <xsl:attribute name="width"><xsl:value-of select="@width"/></xsl:attribute>
                                <div align="left"><xsl:value-of select="@data"/></div>    
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


            </xsl:element>
        </font>
        </xsl:for-each> 
        <!-- anti-fugal micno test results-->
        
            <xsl:for-each select="antifungalmicno">
            <font size="2">
            <xsl:element name="div">
                
                <table cellpadding="0" cellspacing="0" width="100%"><tr><td width="100%">
                <table width="100%">
                            <tr><td width="34%"><div align="left"><b>Antifungals / Organism </b></div></td>
                        <xsl:for-each select="rows/organismrow">
                            <xsl:element name="td"><xsl:attribute name="width"><xsl:value-of select="@width"/></xsl:attribute>
                                <div align="left"><b><xsl:value-of select="@value"/></b></div>
                            </xsl:element>
                        </xsl:for-each>                    
                </tr>
                </table>
                <xsl:for-each select="antifungalclasses">
                        <xsl:for-each select="antifungalclass">
                        <table width="100%">
                        <tr><td width="34%"><div align="left"><b><xsl:value-of select="@value"/></b></div></td>
                        <xsl:element name="td"><xsl:attribute name="width"><xsl:value-of select="@width"/></xsl:attribute>
                                <div align="left"><b><font size="2">MIC No</font></b></div>
                            </xsl:element>
                        <xsl:element name="td"><xsl:attribute name="width"><xsl:value-of select="@width"/></xsl:attribute>
                                <div align="left"><b><font size="2">Sensitivity</font></b></div>
                            </xsl:element>
                        </tr>
                        <xsl:for-each select="antifungal">
                        <tr><td width="34%"><div align="left"><b><xsl:value-of select="@value"/></b></div></td>
                            <xsl:for-each select="organism">
                            <xsl:element name="td">
                                <xsl:attribute name="width"><xsl:value-of select="@width"/></xsl:attribute>
                                <div align='left'><xsl:value-of select="@micno"/></div>    
                            </xsl:element>
                            <xsl:element name="td">
                                <xsl:attribute name="width"><xsl:value-of select="@width"/></xsl:attribute>
                                <div align="left"><xsl:value-of select="@data"/></div>    
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
                <table width="100%"><tr><td width="100%">
            <fieldset><font size="2"><u>Interpretation</u>: 
                        <p>S: Sensitive ,  R: Resistance ,  IS: Intermediate Sensitive and -:Not Done</p></font>
            </fieldset></td>
                        </tr></table>

            </xsl:element>
        </font>
        </xsl:for-each>    
            
    <!-- Histo Topography test results-->
        
            <xsl:for-each select="histoTopographyDetails">
            <font size="2">
            <xsl:element name="div">
                <xsl:attribute name="id">divId_<xsl:value-of select="@name"/></xsl:attribute>
                <table width="100%" cellspacing="1" cellpadding="1" ><tr><td width="100%">
                <table width="100%" cellspacing="1" cellpadding="1">
                            <tr><td width="50%"><div align="left"><b><font size="2">Topography</font></b></div></td>
                            <td width="50%"><div align="left"><b><font size="2">Morphology</font></b></div></td>
                            
                        
                </tr>
                </table>
                <xsl:for-each select="histoTopography">
                <table width="100%" cellspacing="1" cellpadding="1"><tr>
                    <xsl:for-each select="topography">
                            <xsl:element name="td">
                                <xsl:attribute name="width">50%</xsl:attribute>
                                <div align='left'><font size="2"><xsl:value-of select="@name"/>
                                    <xsl:if test="@additionalInfo != ''">
                                        (<xsl:value-of select="@additionalInfo"/>)
                                    </xsl:if>
                                </font></div>    
                            </xsl:element>
                            
                    </xsl:for-each>
                    <xsl:for-each select="morphology">
                            <xsl:element name="td">
                                <xsl:attribute name="width">50%</xsl:attribute>
                                <div align='left'><font size="2">
                                <xsl:if test="@prefixName != ''">
                                        <xsl:value-of select="@prefixName"/>-
                                    </xsl:if>
                                    
                                <xsl:value-of select="@name"/>    
                                    
                                <xsl:if test="@suffixName != ''">
                                        -<xsl:value-of select="@suffixName"/>
                                    </xsl:if>    
                                </font></div>
                            </xsl:element>
                    </xsl:for-each>
                </tr>
                </table>
                
                        
                </xsl:for-each>

                
                </td>
                </tr>
                </table>            
            

            </xsl:element>
        </font>    
        </xsl:for-each>  
    </xsl:template>
<xsl:template name="buttonReportNode">

    <xsl:for-each select="investigations">
            <xsl:element name="div">
                <xsl:attribute name="id">divId_<xsl:value-of select="@name"/></xsl:attribute>
                    <xsl:for-each select="investigation">
                        <xsl:value-of disable-output-escaping="yes" select="text()"/>
                    </xsl:for-each>
            </xsl:element>
        </xsl:for-each>
        <xsl:for-each select="siteDiagnosisDetails">
            <xsl:element name="div">
                <xsl:attribute name="id">divId_<xsl:value-of select="@name"/></xsl:attribute>
                <xsl:element name="table">
                <xsl:attribute name="width">100%</xsl:attribute>
                <xsl:element name="tr">
                                <xsl:element name="td">
                                <xsl:attribute name="width">30%</xsl:attribute>
                                <xsl:attribute name="align">left</xsl:attribute>
                                <div align="left"><b>Site</b></div>
                                </xsl:element>
                                <xsl:element name="td">
                                <xsl:attribute name="width">70%</xsl:attribute>
                                <xsl:attribute name="align">left</xsl:attribute>
                                <div align="left"><b>Diagnosis</b></div>    
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
                    </xsl:element>
            </xsl:element>
        </xsl:for-each>
        
        <!-- anti microbial test results-->
        
            <xsl:for-each select="antisusceptibility">
            <xsl:element name="div">
                <table width="100%"><tr><td width="100%">
                <table width="100%">
                            <tr><td width="34%"><div align="left"><b>Antibiotics / Organism </b></div></td>
                        <xsl:for-each select="rows/organismrow">
                            <xsl:element name="td"><xsl:attribute name="width"><xsl:value-of select="@width"/></xsl:attribute>
                                <div align="left"><b><xsl:value-of select="@value"/></b></div>
                            </xsl:element>
                        </xsl:for-each>                    
                </tr>
                </table>
                <xsl:for-each select="antibioticclasses">
                        <xsl:for-each select="antibioticclass">
                        <table width="100%">
                        <tr><td width="34%"><div align="left"><b><xsl:value-of select="@value"/></b></div></td></tr>
                        <xsl:for-each select="antibiotic">
                        <tr><td width="34%"><div align="left"><b><xsl:value-of select="@value"/></b></div></td>
                            <xsl:for-each select="organism">
                            <xsl:element name="td">
                                <xsl:attribute name="width"><xsl:value-of select="@width"/></xsl:attribute>
                                <div align="left"><xsl:value-of select="@data"/></div>    
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


            </xsl:element>
        </xsl:for-each>       
        <!-- anti-fugal micno test results-->
        
            <xsl:for-each select="antifungalmicno">
            <font size="2">
            <xsl:element name="div">
                
                <table cellpadding="0" cellspacing="0" width="100%"><tr><td width="100%">
                <table width="100%">
                            <tr><td width="34%"><div align="left"><b>Antifungals / Organism </b></div></td>
                        <xsl:for-each select="rows/organismrow">
                            <xsl:element name="td"><xsl:attribute name="width"><xsl:value-of select="@width"/></xsl:attribute>
                                <div align="left"><b><xsl:value-of select="@value"/></b></div>
                            </xsl:element>
                        </xsl:for-each>                    
                </tr>
                </table>
                <xsl:for-each select="antifungalclasses">
                        <xsl:for-each select="antifungalclass">
                        <table width="100%">
                        <tr><td width="34%"><div align="left"><b><xsl:value-of select="@value"/></b></div></td>
                        <xsl:element name="td"><xsl:attribute name="width"><xsl:value-of select="@width"/></xsl:attribute>
                                <div align="left"><b><font size="2">MIC No</font></b></div>
                            </xsl:element>
                        <xsl:element name="td"><xsl:attribute name="width"><xsl:value-of select="@width"/></xsl:attribute>
                                <div align="left"><b><font size="2">Sensitivity</font></b></div>
                            </xsl:element>
                        </tr>
                        <xsl:for-each select="antifungal">
                        <tr><td width="34%"><div align="left"><b><xsl:value-of select="@value"/></b></div></td>
                            <xsl:for-each select="organism">
                            <xsl:element name="td">
                                <xsl:attribute name="width"><xsl:value-of select="@width"/></xsl:attribute>
                                <div align='left'><xsl:value-of select="@micno"/></div>    
                            </xsl:element>
                            <xsl:element name="td">
                                <xsl:attribute name="width"><xsl:value-of select="@width"/></xsl:attribute>
                                <div align="left"><xsl:value-of select="@data"/></div>    
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
                <table width="100%"><tr><td width="100%">
            <fieldset><font size="2"><u>Interpretation</u>: 
                        <p>S: Sensitive ,  R: Resistance ,  IS: Intermediate Sensitive and -:Not Done</p></font>
            </fieldset></td>
                        </tr></table>    

            </xsl:element>
        </font>
        </xsl:for-each>        
        <!-- Histo Topography test results-->
        
            <xsl:for-each select="histoTopographyDetails">
            <xsl:element name="div">
                <xsl:attribute name="id">divId_<xsl:value-of select="@name"/></xsl:attribute>
                <table width="100%" cellspacing="1" cellpadding="1" ><tr><td width="100%">
                <table width="100%" cellspacing="1" cellpadding="1">
                            <tr><td width="50%"><div align="left"><b><font size="2">Topography</font></b></div></td>
                            <td width="50%"><div align="left"><b><font size="2">Morphology</font></b></div></td>
                            
                        
                </tr>
                </table>
                <xsl:for-each select="histoTopography">
                <table width="100%" cellspacing="1" cellpadding="1"><tr>
                    <xsl:for-each select="topography">
                            <xsl:element name="td">
                                <xsl:attribute name="width">50%</xsl:attribute>
                                <div align='left'><font size="2"><xsl:value-of select="@name"/>
                                    <xsl:if test="@additionalInfo != ''">
                                        (<xsl:value-of select="@additionalInfo"/>)
                                    </xsl:if>
                                </font></div>    
                            </xsl:element>
                            
                    </xsl:for-each>
                    <xsl:for-each select="morphology">
                            <xsl:element name="td">
                                <xsl:attribute name="width">50%</xsl:attribute>
                                <div align='left'><font size="2">
                                <xsl:if test="@prefixName != ''">
                                        <xsl:value-of select="@prefixName"/>-
                                    </xsl:if>
                                    
                                <xsl:value-of select="@name"/>    
                                    
                                <xsl:if test="@suffixName != ''">
                                        -<xsl:value-of select="@suffixName"/>
                                    </xsl:if>    
                                </font></div>
                            </xsl:element>
                    </xsl:for-each>
                </tr>
                </table>
                
                        
                </xsl:for-each>

                
                </td>
                </tr>
                </table>            
            

            </xsl:element>
        </xsl:for-each>  

    </xsl:template>
<xsl:template name="queryValue">
    <xsl:element name="table">
    <xsl:attribute name="width">100%</xsl:attribute> 
    
    <xsl:for-each select="otherTemplate">
        <xsl:element name="tr">
            <xsl:element name="td"><xsl:attribute name="width">100%</xsl:attribute> 
             <xsl:value-of disable-output-escaping="yes" select="text()"/>
            </xsl:element>
        </xsl:element>
    </xsl:for-each>
    <xsl:for-each select="results"> 
            <xsl:for-each select="resultrow">
                    <xsl:element name="tr">
                        <xsl:for-each select="resultcolumn">
                        <xsl:if test="@valueType = '0' or @valueType ='3'">
                        <xsl:element name="td">
                        <xsl:attribute name="width"><xsl:value-of select="columnwidth"/></xsl:attribute>
                        <font size="3"> <xsl:value-of disable-output-escaping="yes" select="@value"/></font>
                        
                        </xsl:element>
                        </xsl:if>
                        </xsl:for-each>
                    </xsl:element>
            </xsl:for-each>
    <xsl:for-each select="errorResult">
            <xsl:element name="tr">
                <xsl:element name="td">
                <xsl:attribute name="width">100%</xsl:attribute>
                <xsl:value-of disable-output-escaping="yes" select="@value"/>
                </xsl:element>
            </xsl:element>
        </xsl:for-each>
        </xsl:for-each>

    
    </xsl:element>
    </xsl:template>

<xsl:template name="CHECKROWSHOWABLE">
<xsl:choose>
<xsl:when test="columnDetails/table/tr/td/element[@isPrintable='1']">true</xsl:when>
<xsl:otherwise>false</xsl:otherwise>
</xsl:choose> 
</xsl:template>

<xsl:template name="createrequisitionsection">
<xsl:param name="elementCode"/>
<xsl:param name="listIndex"/>
<xsl:param name="multisessionid"/>
<xsl:element name="table">
        <xsl:element name="tr">
                <xsl:for-each select="images">
                    <xsl:for-each select="imageDetails">
                        <xsl:for-each select="imageswithactualaddress">
                            <xsl:for-each select="image">
                            <xsl:element name="td">
                                <xsl:attribute name="align">center</xsl:attribute>    
                                <xsl:variable name="srcStr"><xsl:value-of select="@readingservlet"/>&amp;index=<xsl:value-of select="@index"/>&amp;elementCode=<xsl:value-of select="$elementCode"/>&amp;processid=2&amp;listIndex=<xsl:value-of select="$listIndex"/></xsl:variable>
                                <xsl:variable name="srcStr1"><xsl:value-of disable-output-escaping="no" select="$srcStr"/></xsl:variable>
                                    <xsl:element name="img"> 
                                            <xsl:attribute name="src"><!--<xsl:value-of select="InvestigationGenericFunction:replace($srcStr1,'#','_')" disable-output-escaping="no"/--></xsl:attribute>
                                            <xsl:attribute name="onclick">window.open(this.src,'width=400,height=400')</xsl:attribute>
                                    </xsl:element>
                            </xsl:element>
                            </xsl:for-each>
                        </xsl:for-each>
                    </xsl:for-each>    
                </xsl:for-each>
        </xsl:element>
</xsl:element>
</xsl:template>
<xsl:template name="voValue">
    <xsl:value-of select="@value"/>
</xsl:template>

</xsl:stylesheet>