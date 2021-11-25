// Generated from CleanArchitecture.g4 by ANTLR 4.7.1
package com.example.castaticanalyzer.analyzer.antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CleanArchitectureLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, CLEAN_ARCHITECTURE_UNIT=3;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "CLEAN_ARCHITECTURE_UNIT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'/**'", "'*/'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, "CLEAN_ARCHITECTURE_UNIT"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public CleanArchitectureLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "CleanArchitecture.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\5<\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\5\4;\n\4\2\2\5\3\3\5\4\7\5\3\2\2\2>\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2"+
		"\2\3\t\3\2\2\2\5\r\3\2\2\2\7:\3\2\2\2\t\n\7\61\2\2\n\13\7,\2\2\13\f\7"+
		",\2\2\f\4\3\2\2\2\r\16\7,\2\2\16\17\7\61\2\2\17\6\3\2\2\2\20\21\7B\2\2"+
		"\21\22\7G\2\2\22\23\7p\2\2\23\24\7v\2\2\24\25\7k\2\2\25\26\7v\2\2\26;"+
		"\7{\2\2\27\30\7B\2\2\30\31\7W\2\2\31\32\7u\2\2\32\33\7g\2\2\33\34\7E\2"+
		"\2\34\35\7c\2\2\35\36\7u\2\2\36;\7g\2\2\37 \7B\2\2 !\7K\2\2!\"\7p\2\2"+
		"\"#\7v\2\2#$\7g\2\2$%\7t\2\2%&\7h\2\2&\'\7c\2\2\'(\7e\2\2()\7g\2\2)*\7"+
		"C\2\2*+\7f\2\2+,\7c\2\2,-\7r\2\2-.\7v\2\2./\7g\2\2/;\7t\2\2\60\61\7B\2"+
		"\2\61\62\7H\2\2\62\63\7t\2\2\63\64\7c\2\2\64\65\7o\2\2\65\66\7g\2\2\66"+
		"\67\7y\2\2\678\7q\2\289\7t\2\29;\7m\2\2:\20\3\2\2\2:\27\3\2\2\2:\37\3"+
		"\2\2\2:\60\3\2\2\2;\b\3\2\2\2\4\2:\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}