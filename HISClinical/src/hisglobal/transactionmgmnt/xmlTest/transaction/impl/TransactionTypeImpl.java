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

public class TransactionTypeImpl implements hisglobal.transactionmgmnt.xmlTest.transaction.TransactionType, com.sun.xml.bind.unmarshaller.UnmarshallableObject, com.sun.xml.bind.serializer.XMLSerializable, com.sun.xml.bind.validator.ValidatableObject
{

    protected java.lang.String _PROCESSNAME;
    protected java.lang.String _MODULENAME;
    protected com.sun.xml.bind.util.ListImpl _OPR = new com.sun.xml.bind.util.ListImpl(new java.util.ArrayList());
    private final static com.sun.msv.grammar.Grammar schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize("\u00ac\u00ed\u0000\u0005sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.sun.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/grammar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expression\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0003I\u0000\u000ecachedHashCodeL\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava/lang/Boolean;L\u0000\u000bexpandedExpq\u0000~\u0000\u0002xp\u0001\u00d6p\u0084ppsq\u0000~\u0000\u0000\u0001O\u00d5{ppsr\u0000 com.sun.msv.grammar.OneOrMoreExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001ccom.sun.msv.grammar.UnaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\u0003expq\u0000~\u0000\u0002xq\u0000~\u0000\u0003\u0000\u00c9\u00d9\\ppsr\u0000\'com.sun.msv.grammar.trex.ElementPattern\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\tnameClasst\u0000\u001fLcom/sun/msv/grammar/NameClass;xr\u0000\u001ecom.sun.msv.grammar.ElementExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002Z\u0000\u001aignoreUndeclaredAttributesL\u0000\fcontentModelq\u0000~\u0000\u0002xq\u0000~\u0000\u0003\u0000\u00c9\u00d9Ypp\u0000sq\u0000~\u0000\n\u0000\u00c9\u00d9Npp\u0000sr\u0000\u001dcom.sun.msv.grammar.ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0001\u0000\u00c9\u00d9Cppsq\u0000~\u0000\u0007\u0000\u00c9\u00d98sr\u0000\u0011java.lang.Boolean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000psr\u0000 com.sun.msv.grammar.AttributeExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0002L\u0000\tnameClassq\u0000~\u0000\u000bxq\u0000~\u0000\u0003\u0000\u00c9\u00d95q\u0000~\u0000\u0013psr\u00002com.sun.msv.grammar.Expression$AnyStringExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003\u0000\u0000\u0000\bsq\u0000~\u0000\u0012\u0001q\u0000~\u0000\u0017sr\u0000 com.sun.msv.grammar.AnyNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.sun.msv.grammar.NameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpsr\u00000com.sun.msv.grammar.Expression$EpsilonExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003\u0000\u0000\u0000\tq\u0000~\u0000\u0018psr\u0000#com.sun.msv.grammar.SimpleNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalNamet\u0000\u0012Ljava/lang/String;L\u0000\fnamespaceURIq\u0000~\u0000\u001fxq\u0000~\u0000\u001at\u0000,transactionMgmnt.xmlTest.transaction.OprTypet\u0000+http://java.sun.com/jaxb/xjc/dummy-elementssq\u0000~\u0000\u001et\u0000\u0003OPRt\u0000\u0000sq\u0000~\u0000\u000f\u0000\u0085\u00fc\u001appsq\u0000~\u0000\u0014\u0000\u0085\u00fc\u000fq\u0000~\u0000\u0013psr\u0000\u001bcom.sun.msv.grammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001fLorg/relaxng/datatype/Datatype;L\u0000\u0006exceptq\u0000~\u0000\u0002L\u0000\u0004namet\u0000\u001dLcom/sun/msv/util/StringPair;xq\u0000~\u0000\u0003\u0000I\u00d8\u0096q\u0000~\u0000\u0013psr\u0000#com.sun.msv.datatype.xsd.StringType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001Z\u0000\risAlwaysValidxr\u0000*com.sun.msv.datatype.xsd.BuiltinAtomicType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun.msv.datatype.xsd.ConcreteType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\'com.sun.msv.datatype.xsd.XSDatatypeImpl\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fnamespaceUriq\u0000~\u0000\u001fL\u0000\btypeNameq\u0000~\u0000\u001fL\u0000\nwhiteSpacet\u0000.Lcom/sun/msv/datatype/xsd/WhiteSpaceProcessor;xpt\u0000 http://www.w3.org/2001/XMLSchemat\u0000\u0006stringsr\u0000.com.sun.msv.datatype.xsd.WhiteSpaceProcessor$1\u0013JMoI\u00db\u00a4G\u0002\u0000\u0000xr\u0000,com.sun.msv.datatype.xsd.WhiteSpaceProcessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xp\u0001sr\u00000com.sun.msv.grammar.Expression$NullSetExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003\u0000\u0000\u0000\nppsr\u0000\u001bcom.sun.msv.util.StringPair\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u001fL\u0000\fnamespaceURIq\u0000~\u0000\u001fxpq\u0000~\u00003q\u0000~\u00002sq\u0000~\u0000\u001et\u0000\u000bMODULE_NAMEq\u0000~\u0000%q\u0000~\u0000\u001dsq\u0000~\u0000\u000f\u0000\u0086\u009b\u0004ppsq\u0000~\u0000\u0014\u0000\u0086\u009a\u00f9q\u0000~\u0000\u0013pq\u0000~\u0000+sq\u0000~\u0000\u001et\u0000\fPROCESS_NAMEq\u0000~\u0000%q\u0000~\u0000\u001dsr\u0000\"com.sun.msv.grammar.ExpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\bexpTablet\u0000/Lcom/sun/msv/grammar/ExpressionPool$ClosedHash;xpsr\u0000-com.sun.msv.grammar.ExpressionPool$ClosedHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0002\u0000\u0004I\u0000\u0005countI\u0000\tthresholdL\u0000\u0006parentq\u0000~\u0000B[\u0000\u0005tablet\u0000![Lcom/sun/msv/grammar/Expression;xp\u0000\u0000\u0000\u0007\u0000\u0000\u00009pur\u0000![Lcom.sun.msv.grammar.Expression;\u00d68D\u00c3]\u00ad\u00a7\n\u0002\u0000\u0000xp\u0000\u0000\u0000\u00bfppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppq\u0000~\u0000\u0011ppppppppppq\u0000~\u0000\u0010q\u0000~\u0000\u0005pppppppppppq\u0000~\u0000\u0006pppppppppppq\u0000~\u0000\tppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppq\u0000~\u0000=q\u0000~\u0000&pppppppppppppppp");

    private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
        return hisglobal.transactionmgmnt.xmlTest.transaction.TransactionType.class;
    }

    public java.lang.String getPROCESSNAME() {
        return _PROCESSNAME;
    }

    public void setPROCESSNAME(java.lang.String value) {
        _PROCESSNAME = value;
    }

    public java.lang.String getMODULENAME() {
        return _MODULENAME;
    }

    public void setMODULENAME(java.lang.String value) {
        _MODULENAME = value;
    }

    public java.util.List getOPR() {
        return _OPR;
    }

    public com.sun.xml.bind.unmarshaller.ContentHandlerEx getUnmarshaller(com.sun.xml.bind.unmarshaller.UnmarshallingContext context) {
        return new hisglobal.transactionmgmnt.xmlTest.transaction.impl.TransactionTypeImpl.Unmarshaller(context);
    }

    public java.lang.Class getPrimaryInterfaceClass() {
        return PRIMARY_INTERFACE_CLASS();
    }

    public void serializeElements(com.sun.xml.bind.serializer.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        int idx3 = 0;
        final int len3 = _OPR.size();
        while (idx3 != len3) {
            if (_OPR.get(idx3) instanceof javax.xml.bind.Element) {
                context.childAsElements(((com.sun.xml.bind.serializer.XMLSerializable) _OPR.get(idx3 ++)));
            } else {
                context.startElement("", "OPR");
                int idx_0 = idx3;
                context.childAsAttributes(((com.sun.xml.bind.serializer.XMLSerializable) _OPR.get(idx_0 ++)));
                context.endAttributes();
                context.childAsElements(((com.sun.xml.bind.serializer.XMLSerializable) _OPR.get(idx3 ++)));
                context.endElement();
            }
        }
    }

    public void serializeAttributes(com.sun.xml.bind.serializer.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        int idx3 = 0;
        final int len3 = _OPR.size();
        if (_MODULENAME!= null) {
            context.startAttribute("", "MODULE_NAME");
            try {
                context.text(((java.lang.String) _MODULENAME));
            } catch (java.lang.Exception e) {
                com.sun.xml.bind.marshaller.Util.handlePrintConversionException(this, e, context);
            }
            context.endAttribute();
        }
        if (_PROCESSNAME!= null) {
            context.startAttribute("", "PROCESS_NAME");
            try {
                context.text(((java.lang.String) _PROCESSNAME));
            } catch (java.lang.Exception e) {
                com.sun.xml.bind.marshaller.Util.handlePrintConversionException(this, e, context);
            }
            context.endAttribute();
        }
    }

    public void serializeAttributeBodies(com.sun.xml.bind.serializer.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        int idx3 = 0;
        final int len3 = _OPR.size();
    }

    public java.lang.Class getPrimaryInterface() {
        return (hisglobal.transactionmgmnt.xmlTest.transaction.TransactionType.class);
    }

    public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
        return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
    }

    public class Unmarshaller
        extends com.sun.xml.bind.unmarshaller.ContentHandlerEx
    {


        public Unmarshaller(com.sun.xml.bind.unmarshaller.UnmarshallingContext context) {
            super(context, "--------");
        }

        protected com.sun.xml.bind.unmarshaller.UnmarshallableObject owner() {
            return hisglobal.transactionmgmnt.xmlTest.transaction.impl.TransactionTypeImpl.this;
        }

        public void enterElement(java.lang.String ___uri, java.lang.String ___local, org.xml.sax.Attributes __atts)
            throws com.sun.xml.bind.unmarshaller.UnreportedException
        {
            switch (state) {
                case  5 :
                    if (("" == ___uri)&&("OPR" == ___local)) {
                        context.pushAttributes(__atts);
                        goto3();
                        return ;
                    }
                    revertToParentFromEnterElement(___uri, ___local, __atts);
                    return ;
                case  3 :
                    if (("" == ___uri)&&("BLOCK" == ___local)) {
                        _OPR.add(((hisglobal.transactionmgmnt.xmlTest.transaction.impl.OprTypeImpl) spawnChildFromEnterElement((hisglobal.transactionmgmnt.xmlTest.transaction.impl.OprTypeImpl.class), 4, ___uri, ___local, __atts)));
                        return ;
                    }
                    break;
                case  0 :
                    if (("" == ___uri)&&("OPR" == ___local)) {
                        context.pushAttributes(__atts);
                        goto3();
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
                case  4 :
                    if (("" == ___uri)&&("OPR" == ___local)) {
                        context.popAttributes();
                        state = 5;
                        return ;
                    }
                    break;
                case  5 :
                    revertToParentFromLeaveElement(___uri, ___local);
                    return ;
            }
            super.leaveElement(___uri, ___local);
        }

        public void enterAttribute(java.lang.String ___uri, java.lang.String ___local)
            throws com.sun.xml.bind.unmarshaller.UnreportedException
        {
            switch (state) {
                case  5 :
                    revertToParentFromEnterAttribute(___uri, ___local);
                    return ;
                case  3 :
                    if (("" == ___uri)&&("QRY_TOT_SIZE" == ___local)) {
                        _OPR.add(((hisglobal.transactionmgmnt.xmlTest.transaction.impl.OprTypeImpl) spawnChildFromEnterAttribute((hisglobal.transactionmgmnt.xmlTest.transaction.impl.OprTypeImpl.class), 4, ___uri, ___local)));
                        return ;
                    }
                    if (("" == ___uri)&&("BLOCK_SIZE" == ___local)) {
                        _OPR.add(((hisglobal.transactionmgmnt.xmlTest.transaction.impl.OprTypeImpl) spawnChildFromEnterAttribute((hisglobal.transactionmgmnt.xmlTest.transaction.impl.OprTypeImpl.class), 4, ___uri, ___local)));
                        return ;
                    }
                    if (("" == ___uri)&&("NAME" == ___local)) {
                        _OPR.add(((hisglobal.transactionmgmnt.xmlTest.transaction.impl.OprTypeImpl) spawnChildFromEnterAttribute((hisglobal.transactionmgmnt.xmlTest.transaction.impl.OprTypeImpl.class), 4, ___uri, ___local)));
                        return ;
                    }
                    if (("" == ___uri)&&("TRN_MGMT" == ___local)) {
                        _OPR.add(((hisglobal.transactionmgmnt.xmlTest.transaction.impl.OprTypeImpl) spawnChildFromEnterAttribute((hisglobal.transactionmgmnt.xmlTest.transaction.impl.OprTypeImpl.class), 4, ___uri, ___local)));
                        return ;
                    }
                    if (("" == ___uri)&&("ID" == ___local)) {
                        _OPR.add(((hisglobal.transactionmgmnt.xmlTest.transaction.impl.OprTypeImpl) spawnChildFromEnterAttribute((hisglobal.transactionmgmnt.xmlTest.transaction.impl.OprTypeImpl.class), 4, ___uri, ___local)));
                        return ;
                    }
                    break;
                case  0 :
                    if (("" == ___uri)&&("MODULE_NAME" == ___local)) {
                        state = 6;
                        return ;
                    }
                    if (("" == ___uri)&&("PROCESS_NAME" == ___local)) {
                        state = 1;
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
                case  7 :
                    if (("" == ___uri)&&("MODULE_NAME" == ___local)) {
                        goto0();
                        return ;
                    }
                    break;
                case  5 :
                    revertToParentFromLeaveAttribute(___uri, ___local);
                    return ;
                case  2 :
                    if (("" == ___uri)&&("PROCESS_NAME" == ___local)) {
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
                    case  1 :
                        try {
                            _PROCESSNAME = value;
                        } catch (java.lang.Exception e) {
                            handleParseConversionException(e);
                        }
                        state = 2;
                        return ;
                    case  5 :
                        revertToParentFromText(value);
                        return ;
                    case  6 :
                        try {
                            _MODULENAME = value;
                        } catch (java.lang.Exception e) {
                            handleParseConversionException(e);
                        }
                        state = 7;
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
                case  4 :
                    state = 4;
                    return ;
            }
            super.leaveChild(nextState);
        }

        private void goto3()
            throws com.sun.xml.bind.unmarshaller.UnreportedException
        {
            int idx;
            state = 3;
            idx = context.getAttribute("", "NAME");
            if (idx >= 0) {
                context.consumeAttribute(idx);
                return ;
            }
            idx = context.getAttribute("", "TRN_MGMT");
            if (idx >= 0) {
                context.consumeAttribute(idx);
                return ;
            }
            idx = context.getAttribute("", "BLOCK_SIZE");
            if (idx >= 0) {
                context.consumeAttribute(idx);
                return ;
            }
            idx = context.getAttribute("", "ID");
            if (idx >= 0) {
                context.consumeAttribute(idx);
                return ;
            }
            idx = context.getAttribute("", "QRY_TOT_SIZE");
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
            idx = context.getAttribute("", "MODULE_NAME");
            if (idx >= 0) {
                context.consumeAttribute(idx);
                return ;
            }
            idx = context.getAttribute("", "PROCESS_NAME");
            if (idx >= 0) {
                context.consumeAttribute(idx);
                return ;
            }
        }

    }

}
