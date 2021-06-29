<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" indent="yes"/>
	<xsl:template match="/">
	<xsl:param name="elementsequence">0</xsl:param>
	<xsl:param name="workOrder">0</xsl:param>
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
														<xsl:attribute name="width">100%</xsl:attribute>
															<xsl:element name="td">
																<xsl:for-each select="element">
																	<xsl:if test="@idC = 'input'">
																	<xsl:if test="@type != 'checkbox'">
																		<xsl:call-template name="INPUT" >
																		<xsl:with-param name="tavIndex" select="$elementsequence + 1"/>
																		</xsl:call-template>
																	</xsl:if>
																	<xsl:if test="@type = 'checkbox'">
																		<xsl:call-template name="htmlcheckbox" >
																		<xsl:with-param name="tavIndex" select="$elementsequence + 1"/>
																		</xsl:call-template>
																	</xsl:if>
																	</xsl:if>

																	<xsl:if test="@idC = 'textArea'">
																		<xsl:call-template name="TextArea">
																		<xsl:with-param name="tavIndex"  select="$elementsequence + 1"/>
																		</xsl:call-template>

																	</xsl:if>
																		<xsl:if test="@idC = 'radio'">
																		<xsl:call-template name="htmlradio">
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
																	<xsl:for-each select="rangetag">
																		<xsl:element name="b">
																			<xsl:call-template name="DISPLAYRANGETAG"/>
																		</xsl:element>
																	</xsl:for-each>
																	<xsl:if test="@idC = 'queryValue'">
																		<xsl:call-template name="queryValue"/>
																</xsl:if>
																	<xsl:if test="@idC = 'button'">
																	<xsl:attribute name="id"><xsl:value-of select="@name"/></xsl:attribute>
																	<xsl:call-template name="button"/>
																</xsl:if>
																<xsl:if test="@idC = 'imagesection'">
																	<xsl:attribute name="id">tdimagesection<xsl:value-of select="@name"/>#<xsl:value-of select="$workOrder"/></xsl:attribute>
																	<xsl:call-template name="imagesection">
																	<xsl:with-param name="workOrder"><xsl:value-of select="$workOrder"/></xsl:with-param>
																	</xsl:call-template>
																	<xsl:element name="div">
																		<xsl:attribute name="id"><xsl:value-of select="$workOrder"/>#<xsl:value-of select="@name"/></xsl:attribute>
																		<xsl:call-template name="createimagesection"><xsl:with-param name="elementCode"><xsl:value-of select="@name"/></xsl:with-param></xsl:call-template>
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

<xsl:template name="htmlcheckbox" >
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
			<xsl:if test="@readOnly!=''">	
			<xsl:attribute name="readOnly">
				<xsl:if test="@readOnly='0'">true</xsl:if>
				<xsl:if test="@readOnly='0'">false</xsl:if>
			</xsl:attribute>
			</xsl:if>
			
			<xsl:attribute name="value">
					<xsl:value-of select="@defaultValue"/>
			</xsl:attribute>
			<xsl:if test="@value='0'">
				<xsl:attribute name="checked"></xsl:attribute>
			</xsl:if>
			<xsl:if test="@value='1'">
				<xsl:attribute name="value">
					<xsl:value-of select="@value"/>
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
			<xsl:attribute name="tabIndex">
			<xsl:value-of select="$tavIndex"/>
			</xsl:attribute>
			<xsl:attribute name="onFocus">callOnFocus(this)</xsl:attribute>
			<xsl:if test="@name!=''">
			<xsl:attribute name="name">
				<xsl:value-of select="@name"/>
			</xsl:attribute>
			<xsl:attribute name="id">
				<xsl:value-of select="@name"/>
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
			<xsl:if test="@value != ''">
			<xsl:value-of select="@value"/>
			</xsl:if>
			<xsl:if test="@value = ''">
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
			<xsl:attribute name="value">
			<xsl:value-of select="$val"/>
			</xsl:attribute>

			<xsl:variable name="defaultval" select="@defaultValue"/>
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

	<xsl:template name="htmlradio">
	<xsl:param name="tavIndex" />
		
			<xsl:variable name="val" select="@value"/>
			<xsl:variable name="defaultval" select="@defaultValue"/>
			<xsl:for-each select="options">
				<xsl:for-each select="option">
				<xsl:value-of select="@label"/>
				<xsl:element name="input">
					<xsl:attribute name="type">radio</xsl:attribute>
					<xsl:attribute name="value"><xsl:value-of select="@value"/></xsl:attribute>
				<xsl:if test="$val != ''">
					<xsl:if test="$val = @value">
							<xsl:attribute name="checked">checked</xsl:attribute>
					</xsl:if>
				</xsl:if>

				<xsl:if test="$val = ''">
					<xsl:if test="$defaultval = @value">
						<xsl:attribute name="checked">checked</xsl:attribute>
					</xsl:if>
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
				</xsl:for-each>
			</xsl:for-each>
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

<xsl:template match="table">
	
</xsl:template>
<xsl:template name="queryValue" >
	<xsl:element name="table">
	<xsl:attribute name="width">100%</xsl:attribute> 
	
	<xsl:for-each select="otherTemplate">
		<xsl:element name="tr">
			<xsl:element name="td"><xsl:attribute name="width">100%</xsl:attribute> 
			<xsl:value-of select="text()" disable-output-escaping="yes"/>
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
						<xsl:value-of select="@value"/>
						
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



<xsl:template name="button" >
	<xsl:param name="index"></xsl:param>
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
			<xsl:attribute name="value">
				<xsl:value-of select="@value"/>
			</xsl:attribute>
			</xsl:if>
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
<xsl:template name="imagesection" >
	<xsl:param name="workOrder"></xsl:param>
	<xsl:element name="input">
			<xsl:if test="@idC!=''">
				<xsl:attribute name="Type">
					button
				</xsl:attribute>
			</xsl:if>
			<xsl:if test="@name!=''">
			<xsl:attribute name="name">
				<xsl:value-of select="$workOrder"/>#<xsl:value-of select="@name"/>
			</xsl:attribute>
			</xsl:if>
			<xsl:attribute name="onFocus">callOnFocus(this)</xsl:attribute>
			<xsl:if test="@value!=''">
			<xsl:attribute name="value">
				<xsl:value-of select="@value"/>
			</xsl:attribute>
			</xsl:if>
			<xsl:attribute name="style">background-image: url('/AHIMS/hisglobal/images/blankButton.png');width:155px;height:25px;font-size:2px;font-weight:bold;text-align: center;</xsl:attribute>
			<xsl:attribute name="onclick">return openImageUtility('<xsl:value-of select="@callUrl"/>',this,'<xsl:value-of select="@name"/>');</xsl:attribute>
			
	</xsl:element>


		
	</xsl:template>
<xsl:template name="DISPLAYRANGETAG">
<font size="2">
<xsl:if test="@rangeto = '' and @rangefrom = ''">--</xsl:if>

<xsl:if test="@rangeto != '' and @rangefrom != ''">
	<xsl:value-of select="@rangefrom"/> <xsl:value-of select="@rangefromunit"/> - <xsl:value-of select="@rangeto"/><xsl:value-of select="@rangefromunit"/>
</xsl:if>

<xsl:if test="@rangefrom = '' and @rangeto != ''">
	&lt;<xsl:value-of select="@rangeto"/><xsl:value-of select="@rangetounit"/>
</xsl:if>

<xsl:if test="@rangeto = '' and @rangefrom != ''">
	&gt; <xsl:value-of select="@rangefrom"/><xsl:value-of select="@rangefromunit"/>
</xsl:if>
</font>
</xsl:template>

<xsl:template name="createimagesection">
<xsl:param name="elementCode"></xsl:param>
<xsl:element name="table">
		<xsl:element name="tr">
				<xsl:for-each select="images">
					<xsl:for-each select="imageDetails">
						<xsl:for-each select="imageservletdirect">
							<xsl:for-each select="image">
							<xsl:element name="td">
								<xsl:attribute name="align">center</xsl:attribute>	
								<xsl:variable name="srcStr"><xsl:value-of select="@readingservlet"/>&amp;index=<xsl:value-of select="@index"/>&amp;elementCode=<xsl:value-of select="$elementCode"/></xsl:variable>
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


</xsl:stylesheet>
