//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2007.02.05 at 03:52:32 PST
//


package hisglobal.transactionmgmnt.xmlTest.transaction.impl;

/*
Developed By          : Partha P Chattaraj
Creation Dated        : 17-06-2006
Modification Dated    : 07-05-2008
Version               : HIMS 2.0

*/

public class BlockTypeImpl implements hisglobal.transactionmgmnt.xmlTest.transaction.BlockType, com.sun.xml.bind.unmarshaller.UnmarshallableObject, com.sun.xml.bind.serializer.XMLSerializable, com.sun.xml.bind.validator.ValidatableObject
{

    protected java.lang.String _ID;
    protected java.lang.String _QRYSIZE;
    protected java.lang.String _MANDATORY;
    protected com.sun.xml.bind.util.ListImpl _QRY = new com.sun.xml.bind.util.ListImpl(new java.util.ArrayList());
    protected java.lang.String _ROWSINS;
    private final static com.sun.msv.grammar.Grammar schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize("\u00ac\u00ed\u0000\u0005sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.sun.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/grammar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expression\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0003I\u0000\u000ecachedHashCodeL\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava/lang/Boolean;L\u0000\u000bexpandedExpq\u0000~\u0000\u0002xp\u0004Jm\u0005ppsq\u0000~\u0000\u0000\u0003+U\u0003ppsq\u0000~\u0000\u0000\u0002\u00de\u00bf\"ppsq\u0000~\u0000\u0000\u0001\u00fb\u00ba\u00cdppsr\u0000 com.sun.msv.grammar.OneOrMoreExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001ccom.sun.msv.grammar.UnaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\u0003expq\u0000~\u0000\u0002xq\u0000~\u0000\u0003\u0000\u00c9\u00d9\\ppsr\u0000\'com.sun.msv.grammar.trex.ElementPattern\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\tnameClasst\u0000\u001fLcom/sun/msv/grammar/NameClass;xr\u0000\u001ecom.sun.msv.grammar.ElementExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002Z\u0000\u001aignoreUndeclaredAttributesL\u0000\fcontentModelq\u0000~\u0000\u0002xq\u0000~\u0000\u0003\u0000\u00c9\u00d9Ypp\u0000sq\u0000~\u0000\f\u0000\u00c9\u00d9Npp\u0000sr\u0000\u001dcom.sun.msv.grammar.ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0001\u0000\u00c9\u00d9Cppsq\u0000~\u0000\t\u0000\u00c9\u00d98sr\u0000\u0011java.lang.Boolean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000psr\u0000 com.sun.msv.grammar.AttributeExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0002L\u0000\tnameClassq\u0000~\u0000\rxq\u0000~\u0000\u0003\u0000\u00c9\u00d95q\u0000~\u0000\u0015psr\u00002com.sun.msv.grammar.Expression$AnyStringExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003\u0000\u0000\u0000\bsq\u0000~\u0000\u0014\u0001q\u0000~\u0000\u0019sr\u0000 com.sun.msv.grammar.AnyNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.sun.msv.grammar.NameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpsr\u00000com.sun.msv.grammar.Expression$EpsilonExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003\u0000\u0000\u0000\tq\u0000~\u0000\u001apsr\u0000#com.sun.msv.grammar.SimpleNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalNamet\u0000\u0012Ljava/lang/String;L\u0000\fnamespaceURIq\u0000~\u0000!xq\u0000~\u0000\u001ct\u00006transactionMgmnt.xmlTest.transaction.BlockType.QRYTypet\u0000+http://java.sun.com/jaxb/xjc/dummy-elementssq\u0000~\u0000 t\u0000\u0003QRYt\u0000\u0000sq\u0000~\u0000\u0011\u00011\u00e1lppsq\u0000~\u0000\u0016\u00011\u00e1aq\u0000~\u0000\u0015psr\u0000\u001bcom.sun.msv.grammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001fLorg/relaxng/datatype/Datatype;L\u0000\u0006exceptq\u0000~\u0000\u0002L\u0000\u0004namet\u0000\u001dLcom/sun/msv/util/StringPair;xq\u0000~\u0000\u0003\u0000I\u00d8\u0096q\u0000~\u0000\u0015psr\u0000#com.sun.msv.datatype.xsd.StringType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001Z\u0000\risAlwaysValidxr\u0000*com.sun.msv.datatype.xsd.BuiltinAtomicType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun.msv.datatype.xsd.ConcreteType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\'com.sun.msv.datatype.xsd.XSDatatypeImpl\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fnamespaceUriq\u0000~\u0000!L\u0000\btypeNameq\u0000~\u0000!L\u0000\nwhiteSpacet\u0000.Lcom/sun/msv/datatype/xsd/WhiteSpaceProcessor;xpt\u0000 http://www.w3.org/2001/XMLSchemat\u0000\u0006stringsr\u0000.com.sun.msv.datatype.xsd.WhiteSpaceProcessor$1\u0013JMoI\u00db\u00a4G\u0002\u0000\u0000xr\u0000,com.sun.msv.datatype.xsd.WhiteSpaceProcessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xp\u0001sr\u00000com.sun.msv.grammar.Expression$NullSetExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003\u0000\u0000\u0000\nppsr\u0000\u001bcom.sun.msv.util.StringPair\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000!L\u0000\fnamespaceURIq\u0000~\u0000!xpq\u0000~\u00005q\u0000~\u00004sq\u0000~\u0000 t\u0000\u0002IDq\u0000~\u0000\'q\u0000~\u0000\u001fsq\u0000~\u0000\u0011\u0000\u00e3\u0004Pppsq\u0000~\u0000\u0016\u0000\u00e3\u0004Eq\u0000~\u0000\u0015pq\u0000~\u0000-sq\u0000~\u0000 t\u0000\tMANDATORYq\u0000~\u0000\'q\u0000~\u0000\u001fsq\u0000~\u0000\u0011\u0000L\u0095\u00dcppsq\u0000~\u0000\u0016\u0000L\u0095\u00d1q\u0000~\u0000\u0015pq\u0000~\u0000-sq\u0000~\u0000 t\u0000\bQRY_SIZEq\u0000~\u0000\'q\u0000~\u0000\u001fsq\u0000~\u0000\u0011\u0001\u001f\u0017\u00fdppsq\u0000~\u0000\u0016\u0001\u001f\u0017\u00f2q\u0000~\u0000\u0015pq\u0000~\u0000-sq\u0000~\u0000 t\u0000\bROWS_INSq\u0000~\u0000\'q\u0000~\u0000\u001fsr\u0000\"com.sun.msv.grammar.ExpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\bexpTablet\u0000/Lcom/sun/msv/grammar/ExpressionPool$ClosedHash;xpsr\u0000-com.sun.msv.grammar.ExpressionPool$ClosedHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0002\u0000\u0004I\u0000\u0005countI\u0000\tthresholdL\u0000\u0006parentq\u0000~\u0000L[\u0000\u0005tablet\u0000![Lcom/sun/msv/grammar/Expression;xp\u0000\u0000\u0000\u000b\u0000\u0000\u00009pur\u0000![Lcom.sun.msv.grammar.Expression;\u00d68D\u00c3]\u00ad\u00a7\n\u0002\u0000\u0000xp\u0000\u0000\u0000\u00bfppq\u0000~\u0000Cpppppppppppppppppppq\u0000~\u0000?ppppppppppppppppppppppppppppppppppppppppppq\u0000~\u0000\u0005q\u0000~\u0000\u0013ppppppppppq\u0000~\u0000\u0012pppq\u0000~\u0000\bppppppppppppppppppppq\u0000~\u0000\u000bpppppq\u0000~\u0000\u0007ppppppq\u0000~\u0000\u0006ppppppppppppppppppppq\u0000~\u0000Gppppppppppppppppppppppppppppq\u0000~\u0000(ppppppppppppppppppppppppp");

    private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
        return hisglobal.transactionmgmnt.xmlTest.transaction.BlockType.class;
    }

    public java.lang.String getID() {
        return _ID;
    }

    public void setID(java.lang.String value) {
        _ID = value;
    }

    public java.lang.String getQRYSIZE() {
        return _QRYSIZE;
    }

    public void setQRYSIZE(java.lang.String value) {
        _QRYSIZE = value;
    }

    public java.lang.String getMANDATORY() {
        return _MANDATORY;
    }

    public void setMANDATORY(java.lang.String value) {
        _MANDATORY = value;
    }

    public java.util.List getQRY() {
        return _QRY;
    }

    public java.lang.String getROWSINS() {
        return _ROWSINS;
    }

    public void setROWSINS(java.lang.String value) {
        _ROWSINS = value;
    }

    public com.sun.xml.bind.unmarshaller.ContentHandlerEx getUnmarshaller(com.sun.xml.bind.unmarshaller.UnmarshallingContext context) {
        return new hisglobal.transactionmgmnt.xmlTest.transaction.impl.BlockTypeImpl.Unmarshaller(context);
    }

    public java.lang.Class getPrimaryInterfaceClass() {
        return PRIMARY_INTERFACE_CLASS();
    }

    public void serializeElements(com.sun.xml.bind.serializer.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        int idx4 = 0;
        final int len4 = _QRY.size();
        while (idx4 != len4) {
            if (_QRY.get(idx4) instanceof javax.xml.bind.Element) {
                context.childAsElements(((com.sun.xml.bind.serializer.XMLSerializable) _QRY.get(idx4 ++)));
            } else {
                context.startElement("", "QRY");
                int idx_0 = idx4;
                context.childAsAttributes(((com.sun.xml.bind.serializer.XMLSerializable) _QRY.get(idx_0 ++)));
                context.endAttributes();
                context.childAsElements(((com.sun.xml.bind.serializer.XMLSerializable) _QRY.get(idx4 ++)));
                context.endElement();
            }
        }
    }

    public void serializeAttributes(com.sun.xml.bind.serializer.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        int idx4 = 0;
        final int len4 = _QRY.size();
        if (_ID!= null) {
            context.startAttribute("", "ID");
            try {
                context.text(((java.lang.String) _ID));
            } catch (java.lang.Exception e) {
                com.sun.xml.bind.marshaller.Util.handlePrintConversionException(this, e, context);
            }
            context.endAttribute();
        }
        if (_MANDATORY!= null) {
            context.startAttribute("", "MANDATORY");
            try {
                context.text(((java.lang.String) _MANDATORY));
            } catch (java.lang.Exception e) {
                com.sun.xml.bind.marshaller.Util.handlePrintConversionException(this, e, context);
            }
            context.endAttribute();
        }
        if (_QRYSIZE!= null) {
            context.startAttribute("", "QRY_SIZE");
            try {
                context.text(((java.lang.String) _QRYSIZE));
            } catch (java.lang.Exception e) {
                com.sun.xml.bind.marshaller.Util.handlePrintConversionException(this, e, context);
            }
            context.endAttribute();
        }
        if (_ROWSINS!= null) {
            context.startAttribute("", "ROWS_INS");
            try {
                context.text(((java.lang.String) _ROWSINS));
            } catch (java.lang.Exception e) {
                com.sun.xml.bind.marshaller.Util.handlePrintConversionException(this, e, context);
            }
            context.endAttribute();
        }
    }

    public void serializeAttributeBodies(com.sun.xml.bind.serializer.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        int idx4 = 0;
        final int len4 = _QRY.size();
    }

    public java.lang.Class getPrimaryInterface() {
        return (hisglobal.transactionmgmnt.xmlTest.transaction.BlockType.class);
    }

    public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
        return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
    }

    public static class QRYTypeImpl implements hisglobal.transactionmgmnt.xmlTest.transaction.BlockType.QRYType, com.sun.xml.bind.unmarshaller.UnmarshallableObject, com.sun.xml.bind.serializer.XMLSerializable, com.sun.xml.bind.validator.ValidatableObject
    {

        protected java.lang.String _ID;
        protected java.lang.String _Value;
        protected java.lang.String _COLSIZE;
        private final static com.sun.msv.grammar.Grammar schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize("\u00ac\u00ed\u0000\u0005sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.sun.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/grammar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expression\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0003I\u0000\u000ecachedHashCodeL\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava/lang/Boolean;L\u0000\u000bexpandedExpq\u0000~\u0000\u0002xp\u0001\u00a4W\u00a1ppsq\u0000~\u0000\u0000\u0001\u0018\u00e1\u00f5ppsr\u0000\u001bcom.sun.msv.grammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001fLorg/relaxng/datatype/Datatype;L\u0000\u0006exceptq\u0000~\u0000\u0002L\u0000\u0004namet\u0000\u001dLcom/sun/msv/util/StringPair;xq\u0000~\u0000\u0003\u0000I\u00d8\u0096sr\u0000\u0011java.lang.Boolean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000psr\u0000#com.sun.msv.datatype.xsd.StringType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001Z\u0000\risAlwaysValidxr\u0000*com.sun.msv.datatype.xsd.BuiltinAtomicType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun.msv.datatype.xsd.ConcreteType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\'com.sun.msv.datatype.xsd.XSDatatypeImpl\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fnamespaceUrit\u0000\u0012Ljava/lang/String;L\u0000\btypeNameq\u0000~\u0000\u0011L\u0000\nwhiteSpacet\u0000.Lcom/sun/msv/datatype/xsd/WhiteSpaceProcessor;xpt\u0000 http://www.w3.org/2001/XMLSchemat\u0000\u0006stringsr\u0000.com.sun.msv.datatype.xsd.WhiteSpaceProcessor$1\u0013JMoI\u00db\u00a4G\u0002\u0000\u0000xr\u0000,com.sun.msv.datatype.xsd.WhiteSpaceProcessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xp\u0001sr\u00000com.sun.msv.grammar.Expression$NullSetExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003\u0000\u0000\u0000\nppsr\u0000\u001bcom.sun.msv.util.StringPair\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u0011L\u0000\fnamespaceURIq\u0000~\u0000\u0011xpq\u0000~\u0000\u0015q\u0000~\u0000\u0014sr\u0000\u001dcom.sun.msv.grammar.ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0001\u0000\u00cf\tZppsr\u0000 com.sun.msv.grammar.AttributeExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0002L\u0000\tnameClasst\u0000\u001fLcom/sun/msv/grammar/NameClass;xq\u0000~\u0000\u0003\u0000\u00cf\tOq\u0000~\u0000\fpq\u0000~\u0000\nsr\u0000#com.sun.msv.grammar.SimpleNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u0011L\u0000\fnamespaceURIq\u0000~\u0000\u0011xr\u0000\u001dcom.sun.msv.grammar.NameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpt\u0000\u0002IDt\u0000\u0000sr\u00000com.sun.msv.grammar.Expression$EpsilonExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003\u0000\u0000\u0000\tsq\u0000~\u0000\u000b\u0001psq\u0000~\u0000\u001d\u0000\u008bu\u00a7ppsq\u0000~\u0000\u001f\u0000\u008bu\u009cq\u0000~\u0000\fpq\u0000~\u0000\nsq\u0000~\u0000\"t\u0000\bCOL_SIZEq\u0000~\u0000&q\u0000~\u0000(sr\u0000\"com.sun.msv.grammar.ExpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\bexpTablet\u0000/Lcom/sun/msv/grammar/ExpressionPool$ClosedHash;xpsr\u0000-com.sun.msv.grammar.ExpressionPool$ClosedHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0002\u0000\u0004I\u0000\u0005countI\u0000\tthresholdL\u0000\u0006parentq\u0000~\u0000/[\u0000\u0005tablet\u0000![Lcom/sun/msv/grammar/Expression;xp\u0000\u0000\u0000\u0004\u0000\u0000\u00009pur\u0000![Lcom.sun.msv.grammar.Expression;\u00d68D\u00c3]\u00ad\u00a7\n\u0002\u0000\u0000xp\u0000\u0000\u0000\u00bfpppppq\u0000~\u0000\u0005ppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppq\u0000~\u0000*pppppq\u0000~\u0000\u001eppppppppppppppppppppq\u0000~\u0000\u0006ppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp");

        private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
            return hisglobal.transactionmgmnt.xmlTest.transaction.BlockType.QRYType.class;
        }

        public java.lang.String getID() {
            return _ID;
        }

        public void setID(java.lang.String value) {
            _ID = value;
        }

        public java.lang.String getValue() {
            return _Value;
        }

        public void setValue(java.lang.String value) {
            _Value = value;
        }

        public java.lang.String getCOLSIZE() {
            return _COLSIZE;
        }

        public void setCOLSIZE(java.lang.String value) {
            _COLSIZE = value;
        }

        public com.sun.xml.bind.unmarshaller.ContentHandlerEx getUnmarshaller(com.sun.xml.bind.unmarshaller.UnmarshallingContext context) {
            return new hisglobal.transactionmgmnt.xmlTest.transaction.impl.BlockTypeImpl.QRYTypeImpl.Unmarshaller(context);
        }

        public java.lang.Class getPrimaryInterfaceClass() {
            return PRIMARY_INTERFACE_CLASS();
        }

        public void serializeElements(com.sun.xml.bind.serializer.XMLSerializer context)
            throws org.xml.sax.SAXException
        {
            try {
                context.text(((java.lang.String) _Value));
            } catch (java.lang.Exception e) {
                com.sun.xml.bind.marshaller.Util.handlePrintConversionException(this, e, context);
            }
        }

        public void serializeAttributes(com.sun.xml.bind.serializer.XMLSerializer context)
            throws org.xml.sax.SAXException
        {
            if (_ID!= null) {
                context.startAttribute("", "ID");
                try {
                    context.text(((java.lang.String) _ID));
                } catch (java.lang.Exception e) {
                    com.sun.xml.bind.marshaller.Util.handlePrintConversionException(this, e, context);
                }
                context.endAttribute();
            }
            if (_COLSIZE!= null) {
                context.startAttribute("", "COL_SIZE");
                try {
                    context.text(((java.lang.String) _COLSIZE));
                } catch (java.lang.Exception e) {
                    com.sun.xml.bind.marshaller.Util.handlePrintConversionException(this, e, context);
                }
                context.endAttribute();
            }
        }

        public void serializeAttributeBodies(com.sun.xml.bind.serializer.XMLSerializer context)
            throws org.xml.sax.SAXException
        {
            try {
                context.text(((java.lang.String) _Value));
            } catch (java.lang.Exception e) {
                com.sun.xml.bind.marshaller.Util.handlePrintConversionException(this, e, context);
            }
        }

        public java.lang.Class getPrimaryInterface() {
            return (hisglobal.transactionmgmnt.xmlTest.transaction.BlockType.QRYType.class);
        }

        public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
            return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
        }

        public class Unmarshaller
            extends com.sun.xml.bind.unmarshaller.ContentHandlerEx
        {


            public Unmarshaller(com.sun.xml.bind.unmarshaller.UnmarshallingContext context) {
                super(context, "------");
            }

            protected com.sun.xml.bind.unmarshaller.UnmarshallableObject owner() {
                return hisglobal.transactionmgmnt.xmlTest.transaction.impl.BlockTypeImpl.QRYTypeImpl.this;
            }

            public void enterElement(java.lang.String ___uri, java.lang.String ___local, org.xml.sax.Attributes __atts)
                throws com.sun.xml.bind.unmarshaller.UnreportedException
            {
                switch (state) {
                    case  1 :
                        revertToParentFromEnterElement(___uri, ___local, __atts);
                        return ;
                }
                super.enterElement(___uri, ___local, __atts);
            }

            public void leaveElement(java.lang.String ___uri, java.lang.String ___local)
                throws com.sun.xml.bind.unmarshaller.UnreportedException
            {
                switch (state) {
                    case  1 :
                        revertToParentFromLeaveElement(___uri, ___local);
                        return ;
                }
                super.leaveElement(___uri, ___local);
            }

            public void enterAttribute(java.lang.String ___uri, java.lang.String ___local)
                throws com.sun.xml.bind.unmarshaller.UnreportedException
            {
                switch (state) {
                    case  0 :
                        if (("" == ___uri)&&("COL_SIZE" == ___local)) {
                            state = 4;
                            return ;
                        }
                        if (("" == ___uri)&&("ID" == ___local)) {
                            state = 2;
                            return ;
                        }
                        break;
                    case  1 :
                        revertToParentFromEnterAttribute(___uri, ___local);
                        return ;
                }
                super.enterAttribute(___uri, ___local);
            }

            public void leaveAttribute(java.lang.String ___uri, java.lang.String ___local)
                throws com.sun.xml.bind.unmarshaller.UnreportedException
            {
                switch (state) {
                    case  5 :
                        if (("" == ___uri)&&("COL_SIZE" == ___local)) {
                            goto0();
                            return ;
                        }
                        break;
                    case  1 :
                        revertToParentFromLeaveAttribute(___uri, ___local);
                        return ;
                    case  3 :
                        if (("" == ___uri)&&("ID" == ___local)) {
                            goto0();
                            return ;
                        }
                        break;
                }
                super.leaveAttribute(___uri, ___local);
            }

            public void text(java.lang.String value)
                throws com.sun.xml.bind.unmarshaller.UnreportedException
            {
                try {
                    switch (state) {
                        case  0 :
                            try {
                                _Value = value;
                            } catch (java.lang.Exception e) {
                                handleParseConversionException(e);
                            }
                            state = 1;
                            return ;
                        case  2 :
                            try {
                                _ID = value;
                            } catch (java.lang.Exception e) {
                                handleParseConversionException(e);
                            }
                            state = 3;
                            return ;
                        case  4 :
                            try {
                                _COLSIZE = value;
                            } catch (java.lang.Exception e) {
                                handleParseConversionException(e);
                            }
                            state = 5;
                            return ;
                        case  1 :
                            revertToParentFromText(value);
                            return ;
                    }
                } catch (java.lang.RuntimeException e) {
                    handleUnexpectedTextException(value, e);
                }
            }

            private void goto0()
                throws com.sun.xml.bind.unmarshaller.UnreportedException
            {
                int idx;
                state = 0;
                idx = context.getAttribute("", "ID");
                if (idx >= 0) {
                    context.consumeAttribute(idx);
                    return ;
                }
                idx = context.getAttribute("", "COL_SIZE");
                if (idx >= 0) {
                    context.consumeAttribute(idx);
                    return ;
                }
            }

        }

    }

    public class Unmarshaller
        extends com.sun.xml.bind.unmarshaller.ContentHandlerEx
    {


        public Unmarshaller(com.sun.xml.bind.unmarshaller.UnmarshallingContext context) {
            super(context, "------------");
        }

        protected com.sun.xml.bind.unmarshaller.UnmarshallableObject owner() {
            return hisglobal.transactionmgmnt.xmlTest.transaction.impl.BlockTypeImpl.this;
        }

        public void enterElement(java.lang.String ___uri, java.lang.String ___local, org.xml.sax.Attributes __atts)
            throws com.sun.xml.bind.unmarshaller.UnreportedException
        {
            switch (state) {
                case  7 :
                    if (("" == ___uri)&&("QRY" == ___local)) {
                        context.pushAttributes(__atts);
                        goto5();
                        return ;
                    }
                    revertToParentFromEnterElement(___uri, ___local, __atts);
                    return ;
                case  0 :
                    if (("" == ___uri)&&("QRY" == ___local)) {
                        context.pushAttributes(__atts);
                        goto5();
                        return ;
                    }
                    break;
            }
            super.enterElement(___uri, ___local, __atts);
        }

        public void leaveElement(java.lang.String ___uri, java.lang.String ___local)
            throws com.sun.xml.bind.unmarshaller.UnreportedException
        {
            switch (state) {
                case  6 :
                    if (("" == ___uri)&&("QRY" == ___local)) {
                        context.popAttributes();
                        state = 7;
                        return ;
                    }
                    break;
                case  7 :
                    revertToParentFromLeaveElement(___uri, ___local);
                    return ;
            }
            super.leaveElement(___uri, ___local);
        }

        public void enterAttribute(java.lang.String ___uri, java.lang.String ___local)
            throws com.sun.xml.bind.unmarshaller.UnreportedException
        {
            switch (state) {
                case  7 :
                    revertToParentFromEnterAttribute(___uri, ___local);
                    return ;
                case  5 :
                    if (("" == ___uri)&&("COL_SIZE" == ___local)) {
                        _QRY.add(((hisglobal.transactionmgmnt.xmlTest.transaction.impl.BlockTypeImpl.QRYTypeImpl) spawnChildFromEnterAttribute((hisglobal.transactionmgmnt.xmlTest.transaction.impl.BlockTypeImpl.QRYTypeImpl.class), 6, ___uri, ___local)));
                        return ;
                    }
                    if (("" == ___uri)&&("ID" == ___local)) {
                        _QRY.add(((hisglobal.transactionmgmnt.xmlTest.transaction.impl.BlockTypeImpl.QRYTypeImpl) spawnChildFromEnterAttribute((hisglobal.transactionmgmnt.xmlTest.transaction.impl.BlockTypeImpl.QRYTypeImpl.class), 6, ___uri, ___local)));
                        return ;
                    }
                    break;
                case  0 :
                    if (("" == ___uri)&&("ID" == ___local)) {
                        state = 1;
                        return ;
                    }
                    if (("" == ___uri)&&("ROWS_INS" == ___local)) {
                        state = 3;
                        return ;
                    }
                    if (("" == ___uri)&&("QRY_SIZE" == ___local)) {
                        state = 10;
                        return ;
                    }
                    if (("" == ___uri)&&("MANDATORY" == ___local)) {
                        state = 8;
                        return ;
                    }
                    break;
            }
            super.enterAttribute(___uri, ___local);
        }

        public void leaveAttribute(java.lang.String ___uri, java.lang.String ___local)
            throws com.sun.xml.bind.unmarshaller.UnreportedException
        {
            switch (state) {
                case  11 :
                    if (("" == ___uri)&&("QRY_SIZE" == ___local)) {
                        goto0();
                        return ;
                    }
                    break;
                case  4 :
                    if (("" == ___uri)&&("ROWS_INS" == ___local)) {
                        goto0();
                        return ;
                    }
                    break;
                case  2 :
                    if (("" == ___uri)&&("ID" == ___local)) {
                        goto0();
                        return ;
                    }
                    break;
                case  7 :
                    revertToParentFromLeaveAttribute(___uri, ___local);
                    return ;
                case  9 :
                    if (("" == ___uri)&&("MANDATORY" == ___local)) {
                        goto0();
                        return ;
                    }
                    break;
            }
            super.leaveAttribute(___uri, ___local);
        }

        public void text(java.lang.String value)
            throws com.sun.xml.bind.unmarshaller.UnreportedException
        {
            try {
                switch (state) {
                    case  8 :
                        try {
                            _MANDATORY = value;
                        } catch (java.lang.Exception e) {
                            handleParseConversionException(e);
                        }
                        state = 9;
                        return ;
                    case  7 :
                        revertToParentFromText(value);
                        return ;
                    case  1 :
                        try {
                            _ID = value;
                        } catch (java.lang.Exception e) {
                            handleParseConversionException(e);
                        }
                        state = 2;
                        return ;
                    case  10 :
                        try {
                            _QRYSIZE = value;
                        } catch (java.lang.Exception e) {
                            handleParseConversionException(e);
                        }
                        state = 11;
                        return ;
                    case  5 :
                        _QRY.add(((hisglobal.transactionmgmnt.xmlTest.transaction.impl.BlockTypeImpl.QRYTypeImpl) spawnChildFromText((hisglobal.transactionmgmnt.xmlTest.transaction.impl.BlockTypeImpl.QRYTypeImpl.class), 6, value)));
                        return ;
                    case  3 :
                        try {
                            _ROWSINS = value;
                        } catch (java.lang.Exception e) {
                            handleParseConversionException(e);
                        }
                        state = 4;
                        return ;
                }
            } catch (java.lang.RuntimeException e) {
                handleUnexpectedTextException(value, e);
            }
        }

        public void leaveChild(int nextState)
            throws com.sun.xml.bind.unmarshaller.UnreportedException
        {
            switch (nextState) {
                case  6 :
                    state = 6;
                    return ;
            }
            super.leaveChild(nextState);
        }

        private void goto5()
            throws com.sun.xml.bind.unmarshaller.UnreportedException
        {
            int idx;
            state = 5;
            idx = context.getAttribute("", "ID");
            if (idx >= 0) {
                context.consumeAttribute(idx);
                return ;
            }
            idx = context.getAttribute("", "COL_SIZE");
            if (idx >= 0) {
                context.consumeAttribute(idx);
                return ;
            }
        }

        private void goto0()
            throws com.sun.xml.bind.unmarshaller.UnreportedException
        {
            int idx;
            state = 0;
            idx = context.getAttribute("", "ID");
            if (idx >= 0) {
                context.consumeAttribute(idx);
                return ;
            }
            idx = context.getAttribute("", "MANDATORY");
            if (idx >= 0) {
                context.consumeAttribute(idx);
                return ;
            }
            idx = context.getAttribute("", "QRY_SIZE");
            if (idx >= 0) {
                context.consumeAttribute(idx);
                return ;
            }
            idx = context.getAttribute("", "ROWS_INS");
            if (idx >= 0) {
                context.consumeAttribute(idx);
                return ;
            }
        }

    }

}
