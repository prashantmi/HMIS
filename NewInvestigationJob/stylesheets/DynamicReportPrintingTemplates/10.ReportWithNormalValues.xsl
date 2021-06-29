<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >
	<xsl:output method="html" indent="yes"/>

	<xsl:param name="normalValues"/>
	<xsl:param name="sampleCode">0</xsl:param>
	<xsl:param name="testName"/>
	<xsl:param name="sessionNo"/>
	<xsl:template match="/">
		<xsl:for-each select="test">
			<xsl:variable name="testname">
				<xsl:value-of select="@testName"/>				
			</xsl:variable>
			<xsl:variable name="samplename">
				<xsl:value-of select = "@sampleName"/>
			</xsl:variable>
			<xsl:variable name="labno">
				<xsl:value-of select = "@labNo"/>
			</xsl:variable>
			<xsl:for-each select="requisitionform">
				<xsl:for-each select="testtemplate">
					<xsl:for-each select="table">
						<xsl:if test="current()/rowDetails">    
							<xsl:element name="table">
								<xsl:attribute name="width">100%</xsl:attribute>
								<xsl:for-each select="rowDetails"> 
									<xsl:variable name="showRow">
										<xsl:call-template name="CHECKROWSHOWABLE"/>
									</xsl:variable>
									<xsl:if test="$showRow='true'">    
										<xsl:element name="tr">
											<xsl:for-each select="columnDetails">
												<xsl:element name="td">
													<xsl:attribute name="colspan">
														<xsl:value-of select="@colspan"/>
													</xsl:attribute>
													<xsl:attribute name="width">
														<xsl:value-of select="@width"/>
													</xsl:attribute>
													<xsl:attribute name="colspan">
														<xsl:value-of select="@colspan"/>
													</xsl:attribute>
													<xsl:element name="div">
														<xsl:attribute name="align">
															<xsl:value-of select="@align"/>
														</xsl:attribute>
														<xsl:for-each select="table">
															<xsl:element name="table">
																<xsl:for-each select="tr">
																	<xsl:element name="tr">
																		<xsl:for-each select="td">
																			<xsl:element name="td">
																				<xsl:for-each select="element">
																					<xsl:if test="@idC = 'input'">
																						<xsl:call-template name="Text" />
																					</xsl:if>
																					<xsl:if test="@idC = 'textArea'">
																						<xsl:call-template name="Text"/>
																					</xsl:if>
																					<xsl:if test="@idC = 'queryValue'">
																						<xsl:call-template name="queryValue"/>
																					</xsl:if>
																					<xsl:if test="@idC = 'Select'">
																						<xsl:variable name="selectVal">
																							<xsl:value-of select="@value"/>
																						</xsl:variable>
																						<xsl:choose>
																							<xsl:when test="@functionName = 'getOrganism'">    
																								<xsl:value-of select="document('organism101.xml')/organism/option[@value = $selectVal]/text()"/>
																							</xsl:when>
																							<xsl:otherwise>
																								<xsl:value-of select="options/option[@value = $selectVal]/@label"/>
																							</xsl:otherwise>
																						</xsl:choose>
																					</xsl:if>
																					<xsl:if test="@idC = 'label'">
																						<xsl:call-template name="Text"/>
																					</xsl:if>
																					<xsl:if test="@idC = 'button'">
																						<xsl:attribute name="id">td#<xsl:value-of select="@name"/>
																						</xsl:attribute>
																						<xsl:call-template name="buttonReport"/>
																					</xsl:if>
																					<xsl:for-each select="rangetag">
																						<xsl:element name="b">
																							<xsl:value-of select="@rangefrom"/>
																							<xsl:value-of select="@rangefromunit" disable-output-escaping="yes"/> - <xsl:value-of select="@rangeto"/>
																							<xsl:value-of select="@rangefromunit" disable-output-escaping="yes"/>
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
									</xsl:if>
								</xsl:for-each>
							</xsl:element>
						</xsl:if>    
					</xsl:for-each>    
				</xsl:for-each>
			</xsl:for-each>
			<xsl:for-each select="sampleform">
				<xsl:for-each select="testtemplate">
					<xsl:if test="current()/rowDetails">    
						<xsl:for-each select="table">
							<xsl:element name="table">
								<xsl:attribute name="width">100%</xsl:attribute>
								<xsl:for-each select="rowDetails"> 
									<xsl:variable name="showRow">
										<xsl:call-template name="CHECKROWSHOWABLE"/>
									</xsl:variable>
									<xsl:if test="$showRow='true'">
										<xsl:element name="tr">
											<xsl:for-each select="columnDetails">
												<xsl:element name="td">
													<xsl:attribute name="colspan">
														<xsl:value-of select="@colspan"/>
													</xsl:attribute>
													<xsl:attribute name="width">
														<xsl:value-of select="@width"/>
													</xsl:attribute>
													<xsl:element name="div">
														<xsl:attribute name="align">
															<xsl:value-of select="@align"/>
														</xsl:attribute>
														<xsl:for-each select="table">
															<xsl:element name="table">
																<xsl:for-each select="tr">
																	<xsl:element name="tr">
																		<xsl:for-each select="td">
																			<xsl:element name="td">
																				<xsl:for-each select="element">
																					<xsl:if test="@idC = 'input'">
																						<xsl:call-template name="Text" />
																					</xsl:if>
																					<xsl:if test="@idC = 'textArea'">
																						<xsl:call-template name="Text"/>
																					</xsl:if>
																					<xsl:if test="@idC = 'Select'">
																						<xsl:variable name="selectVal">
																							<xsl:value-of select="@value"/>
																						</xsl:variable>
																						<xsl:choose>
																							<xsl:when test="@functionName = 'getOrganism'">    
																								<xsl:value-of select="document('organism101.xml')/organism/option[@value = $selectVal]/text()"/>
																							</xsl:when>
																							<xsl:otherwise>
																								<xsl:value-of select="options/option[@value = $selectVal]/@label"/>
																							</xsl:otherwise>
																						</xsl:choose>
																					</xsl:if>
																					<xsl:if test="@idC = 'label'">
																						<xsl:call-template name="Text"/>
																					</xsl:if>
																					<xsl:if test="@idC = 'button'">
																						<xsl:attribute name="id">td#<xsl:value-of select="@name"/>
																						</xsl:attribute>
																						<xsl:call-template name="buttonReport"/>
																					</xsl:if>
																					<xsl:if test="@idC = 'queryValue'">
																						<xsl:call-template name="queryValue"/>
																					</xsl:if>
																					<xsl:for-each select="rangetag">
																						<xsl:element name="b">
																							<xsl:value-of select="@rangefrom"/>
																							<xsl:value-of select="@rangefromunit" disable-output-escaping="yes"/> - <xsl:value-of select="@rangeto"/>
																							<xsl:value-of select="@rangefromunit" disable-output-escaping="yes"/>
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
									</xsl:if>
								</xsl:for-each>
							</xsl:element>
						</xsl:for-each>    
					</xsl:if>
				</xsl:for-each>

			</xsl:for-each>
			<xsl:for-each select="testtemplate">
				<xsl:element name="div">
					<xsl:attribute name="align">left</xsl:attribute>	
					<xsl:element name="font">
						<xsl:attribute name="align">
							<xsl:if test="@size and @size !=''">
								<xsl:value-of select="@size"/>
							</xsl:if>
							<xsl:if test="not(@size) or @size !=''">1</xsl:if>
						</xsl:attribute>											
						<strong>Test Name: 
							<xsl:value-of select="$testname"/>
						      &#160;   Sample Name: 
							<xsl:value-of select="$samplename"/>
						      &#160;   Lab No:  
							<xsl:value-of select="$labno"/>
							
						</strong>
						
					</xsl:element>
				</xsl:element> <br />
				<xsl:text>&#xA;</xsl:text>
				<xsl:for-each select="table">
					<xsl:if test="number(@type) = 2">
						<xsl:if test="rowDetails">            
							<xsl:variable name="maxColumns">
								<xsl:value-of select="@maxColumns"/>
							</xsl:variable>
							<xsl:variable name="maxwidth">
								<xsl:value-of select=" number(60) div number($maxColumns)"/>
							</xsl:variable>
							<xsl:variable name="elementwidth">
								<xsl:value-of select="50"/>
							</xsl:variable>

							<xsl:element name="table">
								<xsl:attribute name="width">100%</xsl:attribute>
								<xsl:attribute name="align">center</xsl:attribute>
								<xsl:attribute name="cellspacing">0</xsl:attribute>
								<xsl:attribute name="cellpadding">0</xsl:attribute>
<!--								<xsl:attribute name="border">1</xsl:attribute>-->
								
								<xsl:for-each select="rowDetails"> 
									<xsl:variable name="showRow">
										<xsl:call-template name="CHECKROWSHOWABLE"/>
									</xsl:variable>
									<xsl:if test="$showRow='true'">
										<xsl:element name="tr">
											<xsl:call-template name="designRows">
												<xsl:with-param name="alreadyAccepted">0</xsl:with-param>
												<xsl:with-param name="maxColumns">
													<xsl:value-of select="$maxColumns"/>
												</xsl:with-param>
												<xsl:with-param name="maxwidth">
													<xsl:value-of select="$maxwidth"/>
												</xsl:with-param>
											</xsl:call-template>
										</xsl:element>
									</xsl:if>
								</xsl:for-each>
							</xsl:element>
						</xsl:if>        
					</xsl:if>
					<xsl:if test="number(@type) != 2">
						<xsl:if test="rowDetails">
							<xsl:element name="table">
								<xsl:attribute name="width">100%</xsl:attribute>
								<xsl:attribute name="align">center</xsl:attribute>
								<xsl:attribute name="cellspacing">0</xsl:attribute>
								<xsl:attribute name="cellpadding">0</xsl:attribute>
								<xsl:element name="tr">
									<xsl:element name="td">
										<xsl:attribute name="width">100%</xsl:attribute>
										<xsl:element name="table">
											<xsl:attribute name="width">100%</xsl:attribute>
											<xsl:attribute name="cellspacing">0</xsl:attribute>
											<xsl:attribute name="cellpadding">0</xsl:attribute>
											<xsl:attribute name="align">center</xsl:attribute>
											<xsl:for-each select="rowDetails"> 
												<xsl:variable name="showRow">
													<xsl:call-template name="CHECKROWSHOWABLE"/>
												</xsl:variable>
												<xsl:if test="$showRow='true'">    
													<xsl:element name="tr">
														<xsl:for-each select="columnDetails">
															<xsl:element name="td">
																<xsl:attribute name="width">
																	<xsl:value-of select="@width"/>
																</xsl:attribute>
																<xsl:attribute name="border">1</xsl:attribute>
																<xsl:element name="div"> 
																	<xsl:attribute name="align">center</xsl:attribute>
																	<xsl:for-each select="table">
																		<xsl:element name="table">
																			<xsl:attribute name="width">100%</xsl:attribute>
																			<xsl:for-each select="tr">
																				<xsl:element name="tr">
																					<xsl:for-each select="td">
																						<xsl:for-each select="element">
																							<xsl:element name="td">
																								<xsl:element name="div">
																									<xsl:attribute name="align">
																										<xsl:if test="@align and @align !=''">
																											<xsl:value-of select="@align"/>
																										</xsl:if>
																										<xsl:if test="not(@align) or @align !=''">left</xsl:if>
																									</xsl:attribute>
																									<xsl:element name="font">
																										<xsl:attribute name="align">
																											<xsl:if test="@size and @size !=''">
																												<xsl:value-of select="@size"/>
																											</xsl:if>
																											<xsl:if test="not(@size) or @size !=''">2</xsl:if>
																										</xsl:attribute>
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
																										</xsl:if>
																										<xsl:if test="@idC = 'label'">
																											<xsl:call-template name="Text"/>
																										</xsl:if>
																										<xsl:if test="@idC = 'button'">
																											<xsl:attribute name="id">td#<xsl:value-of select="@name"/>
																											</xsl:attribute>
																											<xsl:call-template name="buttonReport"/>
																										</xsl:if>
																									</xsl:element>
																								</xsl:element>
																							</xsl:element>
																							<xsl:element name="td">
																								<xsl:element name="div">
																									<xsl:attribute name="align">center</xsl:attribute>
																									<xsl:for-each select="rangetag">
																										<font size="2"> 
																											<xsl:choose>
																												<xsl:when test="@rangeto = '' and @rangefrom = ''">--</xsl:when>
																												<xsl:when test="@rangefrom = null or @rangefrom = ''">
																													<xsl:choose>
																														<xsl:when test="@rangefrom = null or @rangefrom = ''">
                                                                &lt;
																														</xsl:when>
																														<xsl:otherwise>
																															<xsl:value-of select="@rangefrom" />
																															<xsl:value-of select="@rangefromunit" disable-output-escaping="yes"/>
																														</xsl:otherwise> 
																													</xsl:choose>
																												</xsl:when>
																												<xsl:when test="@rangeto = null or @rangeto = ''">
																													<xsl:choose>
																														<xsl:when test="@rangefrom = null or @rangefrom = ''">
                                                                &gt;
																														</xsl:when>
																														<xsl:otherwise>
																															<xsl:value-of select="@rangeto"/>
																															<xsl:value-of select="@rangetounit" disable-output-escaping="yes"/>
																														</xsl:otherwise> 
																													</xsl:choose>
																												</xsl:when>
																												<xsl:otherwise>
																													<xsl:value-of select="@rangefrom"/>
																													<xsl:value-of select="@rangefromunit" disable-output-escaping="yes"/> - <xsl:value-of select="@rangeto"/>
																													<xsl:value-of select="@rangefromunit" disable-output-escaping="yes"/>
																												</xsl:otherwise>
																											</xsl:choose>
																										</font>
																									</xsl:for-each>
																								</xsl:element>
																							</xsl:element>
																						</xsl:for-each>
																					</xsl:for-each>
																				</xsl:element>
																			</xsl:for-each>
																		</xsl:element>
																	</xsl:for-each>
																</xsl:element>
															</xsl:element>
														</xsl:for-each>
													</xsl:element>
												</xsl:if>
											</xsl:for-each>
										</xsl:element>
									</xsl:element>
								</xsl:element>
							</xsl:element>
						</xsl:if>        
					</xsl:if>
				</xsl:for-each>
			</xsl:for-each>

		</xsl:for-each>
	</xsl:template>
	<xsl:template name="Text">
		<xsl:variable name="v" select="@value"/> 
		<xsl:variable name="check">No</xsl:variable>
		<xsl:if test="current()/rangetag">
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
									<b>
										<xsl:value-of select="$v"/>
									</b>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="$v"/>
								</xsl:otherwise>
							</xsl:choose>
						</xsl:if>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:for-each>
		</xsl:if>

		<xsl:if test="not(current()/rangetag)">
			<xsl:value-of select="$v" disable-output-escaping="no"/>
		</xsl:if>
	</xsl:template>

	<xsl:template match="tr"/>

	<xsl:template match="test"/>

	<xsl:template name="designRows">
		<xsl:param name="isAlreadyAccepted"/>
		<xsl:param name="maxColumns"/>
		<xsl:param name="maxwidth"/>
		<xsl:param name="sampleCode"/>
		<!-- Start of if 1 -->
		<xsl:for-each select="child::columnDetails[1]">
			<xsl:call-template name="columnDesignerTD">
				<xsl:with-param name="isAlreadyAccepted">1</xsl:with-param>
				<xsl:with-param name="sampleCode" select="$sampleCode"/>
				<xsl:with-param name="maxColumns">
					<xsl:value-of select="$maxColumns"/>
				</xsl:with-param>
				<xsl:with-param name="maxwidth">
					<xsl:value-of select="$maxwidth"/>
				</xsl:with-param>
			</xsl:call-template>
		</xsl:for-each>
	</xsl:template>
	<xsl:template name="columnDesignerTD">
		<xsl:param name="isAlreadyAccepted"/>
		<xsl:param name="sampleCode"/>
		<xsl:param name="maxColumns"/>
		<xsl:param name="maxwidth"/>
		<xsl:param name="elementwidth"/>
		<!-- <xsl:value-of select="$isAlreadyAccepted"/>a<xsl:value-of select="current()/table/tr/td/element/@idC"/> -->
		<xsl:choose>
			<xsl:when test="($isAlreadyAccepted = '0') and (current()/table/tr/td/element/@idC = '')">
				<!-- Do nothing --> 
			</xsl:when>
			<xsl:otherwise>
				<!--Process the Current Element -->
				<xsl:choose> 
					<xsl:when test="current()/table/tr/td/element/@idC = ''">
						
							
						<xsl:if test="current()/@colNo = number($maxColumns)">
							<td/>
							<xsl:choose>
								<xsl:when test="current()/table/tr/td/element/rangetag">
									<xsl:for-each select="current()/table/tr/td/element/rangetag">
										<xsl:call-template name="DISPLAYRANGETAG"/>
									</xsl:for-each>    
								</xsl:when>
								
							</xsl:choose>
						</xsl:if>
						<!--<xsl:if test="current()/@colNo != '1'">
							<xsl:if test="current()/@colNo != number($maxColumns)-1">
						<td/>
						<xsl:choose>
							<xsl:when test="current()/table/tr/td/element/rangetag">
								<xsl:for-each select="current()/table/tr/td/element/rangetag">
									<xsl:call-template name="DISPLAYRANGETAG"/>
								</xsl:for-each>    
							</xsl:when>
							
						</xsl:choose>
						</xsl:if>
						</xsl:if>-->
						
					</xsl:when>
					<xsl:when test="current()/table/tr/td/element/@idC = 'label'">
						<xsl:if test="current()/table/tr/td/element/@value != ''">							
						 <xsl:element name="td">
							<xsl:choose>
								<!--<xsl:when test="@colNo = 1">
									<xsl:attribute name="colspan">1</xsl:attribute>
									
								</xsl:when>-->
								<xsl:when test="$maxColumns = @colNo">
									<xsl:attribute name="colspan">1</xsl:attribute>
								</xsl:when>
								<xsl:otherwise>
									<!--<xsl:attribute name="colspan">
										<xsl:value-of select="number($maxColumns)-number(current()/@colNo)" />
									</xsl:attribute>
									-->
									<xsl:attribute name="colspan">1</xsl:attribute>
									<!--<xsl:attribute name="width">
										<xsl:value-of select="(number($maxColumns)-number(current()/@colNo)) * number($maxwidth)"/>%</xsl:attribute>-->
									<xsl:attribute name="width">
										<xsl:value-of select="number($maxwidth)+number(10)"/>%</xsl:attribute>
								</xsl:otherwise>
							</xsl:choose>
							<xsl:if test="current()/@colNo != '1'">
								<xsl:attribute name="style">padding-left:<xsl:value-of select="number(current()/@colNo)*number(10)" />px</xsl:attribute>
							</xsl:if>
							<xsl:element name="div">
							<!--	<xsl:attribute name="style">width:40px;</xsl:attribute>-->
								<xsl:attribute name="align">
									<xsl:if test="@align and @align !=''">
										<xsl:value-of select="@align"/>
									</xsl:if>
									<xsl:if test="not(@align) or @align =''">left</xsl:if>
								</xsl:attribute>
								<xsl:element name="font">
									<xsl:attribute name="align">
										<xsl:if test="@size and @size !=''">
											<xsl:value-of select="@size"/>
										</xsl:if>
										<xsl:if test="not(@size) or @size =''">2</xsl:if>
									</xsl:attribute>
									<xsl:if test="current()/table/tr/td/element/@bold ='1'">
										<b>
											<xsl:value-of select="current()/table/tr/td/element/@value"/>
										</b>
									</xsl:if>
									<xsl:if test="current()/table/tr/td/element/@bold = '0'">
										<xsl:value-of select="current()/table/tr/td/element/@value"/>
									</xsl:if>
								</xsl:element>
							</xsl:element>
						</xsl:element>
						</xsl:if>
					</xsl:when>
				
					<xsl:when test="current()/table/tr/td/element/@idC = 'input'">
						<xsl:element name="td">
							<xsl:choose>
								<xsl:when test="$maxColumns = @colNo">
									<xsl:if test="current()/table/tr/td/element/@displayParam = '0'">
										<xsl:attribute name="colspan">2</xsl:attribute>
									</xsl:if>
									<xsl:if test="current()/table/tr/td/element/@displayParam = '1'">
										<xsl:attribute name="colspan">1</xsl:attribute>
									</xsl:if>
									
									<!-- Siddharth -->
									<!--<xsl:attribute name="width">
										<xsl:value-of select="number($maxwidth) + 5"/>%</xsl:attribute>
										-->
								</xsl:when>
								<xsl:otherwise>
									<xsl:if test="current()/table/tr/td/element/@displayParam = '0'">
										<xsl:attribute name="colspan">
											<xsl:value-of select="number($maxColumns)-number(current()/@colNo)" />
										</xsl:attribute>
									</xsl:if>
									<xsl:if test="current()/table/tr/td/element/@displayParam = '1'">
										<xsl:attribute name="colspan">
											<xsl:value-of select="number($maxColumns)-number(current()/@colNo) + 1" />
										</xsl:attribute>
									</xsl:if>
									
									<!-- Siddharth -->
									<!--<xsl:attribute name="width">
										<xsl:value-of select="(number($maxColumns)-number(current()/@colNo)) * number($maxwidth)"/>%</xsl:attribute>-->
								</xsl:otherwise>
							</xsl:choose>
							<xsl:attribute name="style">padding-left:20px</xsl:attribute>
							<xsl:attribute name="align">left</xsl:attribute>
							<xsl:for-each select="current()/table/tr/td/element">
								<xsl:call-template name="Text"/>
							</xsl:for-each>

						</xsl:element>
						<!--<xsl:element name="td">
            <xsl:attribute name="width">40%</xsl:attribute>
            <xsl:attribute name="colspan">1</xsl:attribute>
            <xsl:attribute name="align">center</xsl:attribute>-->
						<xsl:choose>
							<xsl:when test="current()/table/tr/td/element/rangetag">
								<xsl:for-each select="current()/table/tr/td/element/rangetag">
									<xsl:call-template name="DISPLAYRANGETAG"/>
								</xsl:for-each>    
							</xsl:when>
							
						</xsl:choose>
						<!--</xsl:element>-->
					</xsl:when>
					<xsl:when test="current()/table/tr/td/element/@idC = 'Select'">
						<xsl:element name="td">
							<xsl:choose>
								<xsl:when test="$maxColumns = @colNo">
									<xsl:if test="current()/table/tr/td/element/@displayParam = '0'">
										<xsl:attribute name="colspan">2</xsl:attribute>
									</xsl:if>
									<xsl:if test="current()/table/tr/td/element/@displayParam = '1'">
										<xsl:attribute name="colspan">1</xsl:attribute>
									</xsl:if>
									
									<!-- Siddharth -->
									<!--<xsl:attribute name="width">
										<xsl:value-of select="$maxwidth"/>%</xsl:attribute>
										-->
								</xsl:when>

								<xsl:otherwise>
									<xsl:if test="current()/table/tr/td/element/@displayParam = '0'">
										<xsl:attribute name="colspan">
											<xsl:value-of select="number($maxColumns)-number(current()/@colNo)" />
										</xsl:attribute>
									</xsl:if>
									<xsl:if test="current()/table/tr/td/element/@displayParam = '1'">
										<xsl:attribute name="colspan">
											<xsl:value-of select="number($maxColumns)-number(current()/@colNo)+1" />
										</xsl:attribute>
									</xsl:if>
								
									
									<!--<xsl:attribute name="width">
										<xsl:value-of select="(number($maxColumns)-number(current()/@colNo)) * number($maxwidth)"/>%</xsl:attribute>
									-->
								</xsl:otherwise>
								<!--Hack -->



								<!-- Hack End-->

							</xsl:choose>
							<xsl:attribute name="style">padding-left:20px</xsl:attribute>
							<xsl:attribute name="align">left</xsl:attribute>
							<xsl:variable name="selectval">
								<xsl:value-of select="current()/table/tr/td/element/@value"/>
							</xsl:variable> 
							<xsl:choose>
								<xsl:when test="current()/table/tr/td/element/@functionName = 'getOrganism'">    
									<xsl:value-of select="document('organism101.xml')/organism/option[@value = $selectval]/text()"/>
								</xsl:when>
								<xsl:otherwise>

									<xsl:value-of select="current()/table/tr/td/element/options/option[@value = $selectval]/@label"/>
								</xsl:otherwise>
							</xsl:choose>

							<!--
    <xsl:for-each select="options">
                <xsl:for-each select="option">
                                    <xsl:if test="@value = $selectval"> 
                                                        <xsl:value-of select="@label"/>
                                    </xsl:if>
                </xsl:for-each>
    </xsl:for-each>
     <xsl:value-of select="current()/table/tr/td/element/@value"/> -->
						</xsl:element>
						<!--<xsl:element name="td">
            <xsl:attribute name="width">40%</xsl:attribute>
            <xsl:attribute name="colspan">1</xsl:attribute>
            <xsl:attribute name="align">center</xsl:attribute>-->
						<xsl:choose>
							<xsl:when test="current()/table/tr/td/element/rangetag">
								<xsl:for-each select="current()/table/tr/td/element/rangetag">
									<xsl:call-template name="DISPLAYRANGETAG"/>
								</xsl:for-each>    
							</xsl:when>
							<xsl:otherwise>
								<!--<table width="100%"><tr>-->
								<td width="10%" align="center">--</td>
								<td width="30%" align="center">
									<font size="2">--</font>
								</td>
								<!--</tr></table>-->
							</xsl:otherwise>
						</xsl:choose>


						<!--</xsl:element>-->
					</xsl:when>
					<xsl:when test="current()/table/tr/td/element/@idC = 'textArea'">
						<xsl:element name="td">
							<xsl:choose>
								<xsl:when test="$maxColumns =@colNo">
									<xsl:if test="current()/table/tr/td/element/@displayParam = '0'">
										<xsl:attribute name="colspan">2</xsl:attribute>
									</xsl:if>
									<xsl:if test="current()/table/tr/td/element/@displayParam = '1'">
									<xsl:attribute name="colspan">1</xsl:attribute>
									</xsl:if>
									
									<!-- siddharth -->
									<!--<xsl:attribute name="width">
										<xsl:value-of select="$maxwidth"/>%</xsl:attribute>-->
								</xsl:when>
								<xsl:otherwise>
									<xsl:if test="current()/table/tr/td/element/@displayParam = '0'">
										<xsl:attribute name="colspan">
											<xsl:value-of select="number($maxColumns)-number(current()/@colNo)" />
										</xsl:attribute>
									</xsl:if>
									<xsl:if test="current()/table/tr/td/element/@displayParam = '1'">
									<xsl:attribute name="colspan">
										<xsl:value-of select="number($maxColumns)-number(current()/@colNo) + 1" />
									</xsl:attribute>
									</xsl:if>
									<!-- Siddharth -->
									<!--<xsl:attribute name="width">
										<xsl:value-of select="(number($maxColumns)-number(current()/@colNo)) * number($maxwidth)"/>%</xsl:attribute>
										-->
								</xsl:otherwise>
							</xsl:choose>
							<xsl:attribute name="style">padding-left:20px</xsl:attribute>
							<xsl:attribute name="align">left</xsl:attribute>
							<xsl:value-of select="current()/table/tr/td/element/@value" disable-output-escaping="yes"/>
						</xsl:element >
						<xsl:choose>
							<xsl:when test="current()/table/tr/td/element/rangetag">
								<xsl:for-each select="current()/table/tr/td/element/rangetag">
									<xsl:call-template name="DISPLAYRANGETAG"/>
								</xsl:for-each>    
							</xsl:when>
							
						</xsl:choose>
						
					</xsl:when>
					<xsl:when test="current()/table/tr/td/element/@idC = 'button'">

						<xsl:element name="td">
							<xsl:choose>
								<xsl:when test="$maxColumns = @colNo">
									<xsl:attribute name="colspan">1</xsl:attribute>
									<xsl:attribute name="width">
										<xsl:value-of select="$maxwidth"/>%</xsl:attribute>
								</xsl:when>
								<xsl:otherwise>
									<xsl:attribute name="colspan">
										<xsl:value-of select="number($maxColumns)-number(current()/@colNo)" />
									</xsl:attribute>
									<xsl:attribute name="width">
										<xsl:value-of select="(number($maxColumns)-number(current()/@colNo)) * number($maxwidth)"/>%</xsl:attribute>
								</xsl:otherwise>
							</xsl:choose>
							<xsl:attribute name="align">left</xsl:attribute>

							<xsl:for-each select="current()/table/tr/td/element/investigations">
								<xsl:element name="div">
									<xsl:attribute name="id">divId_<xsl:value-of select="@name"/>
									</xsl:attribute>
									<xsl:for-each select="investigation">
										<xsl:value-of select="text()" disable-output-escaping="yes"/>
									</xsl:for-each>
								</xsl:element>
							</xsl:for-each>
							<xsl:for-each select="current()/table/tr/td/element/sitediagnosisdetail">

								<xsl:element name="div">
									<xsl:attribute name="id">divId_<xsl:value-of select="@name"/>
									</xsl:attribute>
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
							<!-- anti microbial test results-->

							<xsl:for-each select="current()/table/tr/td/element/antisusceptibility">
								<xsl:element name="div">

									<table width="100%" cellspacing="0" cellpadding="0">
										<tr>
											<td width="100%">
												<table width="100%" >
													<tr>
														<td width="34%">
															<div align="center">
																<b>Antibiotics / Organism </b>
															</div>
														</td>
														<xsl:for-each select="rows/organismrow">
															<xsl:element name="td">
																<xsl:attribute name="width">
																	<xsl:value-of select="@width"/>
																</xsl:attribute>
																<div align="center">
																	<b>
																		<xsl:value-of select="@value"/>
																	</b>
																</div>
															</xsl:element>
														</xsl:for-each>                    
													</tr>
												</table>
												<xsl:for-each select="antibioticclasses">
													<xsl:for-each select="antibioticclass">
														<table width="100%" >
															<tr>
																<td width="34%">
																	<div align='left'>
																		<b>
																			<xsl:value-of select="@value"/>
																		</b>
																	</div>
																</td>
															</tr>
															<xsl:for-each select="antibiotic">
																<tr>
																	<td width="34%">
																		<div align='right'>
																			<b>
																				<xsl:value-of select="@value"/>
																			</b>
																		</div>
																	</td>
																	<xsl:for-each select="organism">
																		<xsl:element name="td">
																			<xsl:attribute name="width">
																				<xsl:value-of select="@width"/>
																			</xsl:attribute>
																			<div align='center'>
																				<xsl:value-of select="@data"/>
																			</div>    
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


						</xsl:element >
					</xsl:when>
					<xsl:otherwise>
						<td>element rejected</td>
					</xsl:otherwise>
				</xsl:choose>

				<!-- consider the next element -->
				<xsl:choose> 
					<xsl:when test="current()/table/tr/td/element/@idC = '' or current()/table/tr/td/element/@idC = 'label' ">
						<xsl:if test="current()/following-sibling::columnDetails[1]">
							<xsl:for-each select="current()/following-sibling::columnDetails[1]">
								<xsl:call-template name="columnDesignerTD">
									<xsl:with-param name="isAlreadyAccepted">1</xsl:with-param>
									<xsl:with-param name="maxColumns">
										<xsl:value-of select="$maxColumns"/>
									</xsl:with-param>
									<xsl:with-param name="sampleCode">
										<xsl:value-of select="$sampleCode"/>
									</xsl:with-param>
									<xsl:with-param name="maxwidth">
										<xsl:value-of select="$maxwidth"/>
									</xsl:with-param>
								</xsl:call-template>
							</xsl:for-each>
						</xsl:if>
					</xsl:when>
					<xsl:otherwise>
						<xsl:if test="current()/following-sibling::columnDetails[1]">
							<xsl:for-each select="current()/following-sibling::columnDetails[1]">
								<xsl:call-template name="columnDesignerTD">
									<xsl:with-param name="isAlreadyAccepted">0</xsl:with-param>
									<xsl:with-param name="maxColumns">
										<xsl:value-of select="$maxColumns"/>
									</xsl:with-param>
									<xsl:with-param name="sampleCode">
										<xsl:value-of select="$sampleCode"/>
									</xsl:with-param>
									<xsl:with-param name="maxwith">
										<xsl:value-of select="$maxwidth"/>
									</xsl:with-param>
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
		<xsl:variable  name="currentColumnNo">
			<xsl:value-of select="@colNo"/>
		</xsl:variable>
		<xsl:if test="current()/table/tr/td/element">
			<xsl:element name="td">
				<xsl:if test="current()/table/tr/td/element/@idC != '' and current()/table/tr/td/element/@idC != 'label'">
					<xsl:attribute name="colspan">
						<xsl:value-of select="number($maxColumns)- number($currentColumnNo) + 1"/>
					</xsl:attribute>
					<xsl:attribute name="width">
						<xsl:value-of select="(number(100 div number($maxColumns))*(number($maxColumns) - number($currentColumnNo) +1))"/>%</xsl:attribute>
				</xsl:if>
				<xsl:if test="current()/table/tr/td/element/@idC = '' or current()/table/tr/td/element/@idC = 'label' ">
					<xsl:attribute name="width">
						<xsl:value-of select="@width"/>
					</xsl:attribute>
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
											<xsl:attribute name="width">
												<xsl:value-of select="(number($maxColumns)-(number($maxColumns) - number($currentColumnNo)+1))*(100 div number($maxColumns))"/>%</xsl:attribute>
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
															<font size="2"> 
																<xsl:for-each select="rangetag">
																	<xsl:call-template name="DISPLAYRANGETAG"/>
																</xsl:for-each>
															</font>
														</xsl:if>
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
		</xsl:if>
		<!--end of if 1 -->
	</xsl:template>    

	<xsl:template name="getOrganism">
		<xsl:param name="selectedValue">0</xsl:param>
		<!-- <xsl:value-of select="Hello:getHello()"/>-->
		<xsl:for-each select="document('organism101.xml')/organism/option">
			<xsl:if test="$selectedValue != @value">
				<xsl:element name="option">
					<xsl:attribute name="value">
						<xsl:value-of select="@value"/>
					</xsl:attribute>
					<xsl:value-of select="text()"/>
				</xsl:element>
			</xsl:if>
			<xsl:if test="$selectedValue = @value">
				<xsl:element name="option">
					<xsl:attribute name="selected">selected</xsl:attribute>
					<xsl:attribute name="value">
						<xsl:value-of select="@value"/>
					</xsl:attribute>
					<xsl:value-of select="text()"/>
				</xsl:element>
			</xsl:if>
		</xsl:for-each>
	</xsl:template>

	<xsl:template name="selectValue">
		<xsl:param name="sampleCode"/>
		<xsl:variable name="selectVal">
			<xsl:value-of select="@value"/>
		</xsl:variable>

		<xsl:choose>
			<xsl:when test="@functionName = 'getOrganism'">    
				<xsl:value-of select="document('organism101.xml')/organism/option[@value = $selectVal]/text()"/>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="options/option[@value = $selectVal]/@label"/>
			</xsl:otherwise>
		</xsl:choose>

	</xsl:template>
	<xsl:template name="DISPLAYRANGETAG">
		<!--<table width="100%"><tr>-->
		<td width="15%" align="left"> <!-- style="padding-right:20px">-->					
			<xsl:value-of select="@rangefromunit" disable-output-escaping="yes"/>
		</td>
		<td width="20%" align="center"> <!-- style="padding-right:50px">-->
			<font size="2">
				<xsl:if test="@rangeto = '' and @rangefrom = ''">--</xsl:if>

				<xsl:if test="@rangeto != '' and @rangefrom != ''">
					<xsl:value-of select="@rangefrom"/>  - <xsl:value-of select="@rangeto"/>
				</xsl:if>

				<xsl:if test="@rangefrom = '' and @rangeto != ''">
    &lt;<xsl:value-of select="@rangeto"/>
				</xsl:if>

				<xsl:if test="@rangeto = '' and @rangefrom != ''">
    &gt; <xsl:value-of select="@rangefrom"/>
				</xsl:if>
			</font>
		</td>
		<!--</tr></table>-->
	</xsl:template>
	<xsl:template name="buttonReport">
		<xsl:for-each select="investigations">
			<xsl:element name="div">
				<xsl:attribute name="id">divId_<xsl:value-of select="@name"/>
				</xsl:attribute>
				<xsl:for-each select="investigation">
					<xsl:value-of select="text()" disable-output-escaping="yes"/>
				</xsl:for-each>
			</xsl:element>
		</xsl:for-each>
		<xsl:for-each select="sitediagnosisdetail">
			<xsl:element name="div">
				<xsl:attribute name="id">divId_<xsl:value-of select="@name"/>
				</xsl:attribute>
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
		<!-- anti microbial test results-->
		<xsl:for-each select="antisusceptibility">
			<xsl:element name="div">
				<table width="100%" cellspacing="0" cellpadding="0">
					<tr>
						<td width="100%">
							<table width="100%" >
								<tr>
									<td width="34%">
										<div align="center">
											<b>Antibiotics / Organism </b>
										</div>
									</td>
									<xsl:for-each select="rows/organismrow">
										<xsl:element name="td">
											<xsl:attribute name="width">
												<xsl:value-of select="@width"/>
											</xsl:attribute>
											<div align="center">
												<b>
													<xsl:value-of select="@value"/>
												</b>
											</div>
										</xsl:element>
									</xsl:for-each>                    
								</tr>
							</table>
							<xsl:for-each select="antibioticclasses">
								<xsl:for-each select="antibioticclass">
									<table width="100%" >
										<tr>
											<td width="34%">
												<div align='left'>
													<b>
														<xsl:value-of select="@value"/>
													</b>
												</div>
											</td>
										</tr>
										<xsl:for-each select="antibiotic">
											<tr>
												<td width="34%">
													<div align='right'>
														<b>
															<xsl:value-of select="@value"/>
														</b>
													</div>
												</td>
												<xsl:for-each select="organism">
													<xsl:element name="td">
														<xsl:attribute name="width">
															<xsl:value-of select="@width"/>
														</xsl:attribute>
														<div align='center'>
															<xsl:value-of select="@data"/>
														</div>    
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
	</xsl:template>
	<xsl:template name="queryValue" >
		<xsl:element name="table">
			<xsl:attribute name="width">100%</xsl:attribute> 
			<xsl:for-each select="otherTemplate">
				<xsl:element name="tr">
					<xsl:element name="td">
						<xsl:attribute name="width">100%</xsl:attribute> 
						<xsl:value-of select="text()" disable-output-escaping="yes"/>
					</xsl:element>
				</xsl:element>
			</xsl:for-each>

			<xsl:for-each select="results">
				<xsl:for-each  select="resultrow">
					<xsl:element name="tr">
						<xsl:for-each  select="resultcolumn">
							<xsl:if test="@valueType = '0' or @valueType ='3'">
								<xsl:element name="td">
									<xsl:attribute name="width">
										<xsl:value-of select="columnwidth"/>
									</xsl:attribute>
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
	<xsl:template name="CHECKROWSHOWABLE" >
		<xsl:choose>
			<xsl:when test="columnDetails/table/tr/td/element[@isPrintable='1']">true</xsl:when>
			<xsl:otherwise>true</xsl:otherwise>
		</xsl:choose> 
	</xsl:template>
</xsl:stylesheet>