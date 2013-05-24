// http://vimeo.com/groups/29150/videos/8377605
// Split messages
// page
// section
// vspace
// hspace
// destroy
// group

grammar AcumlLang;

options {
	language = Java;
	output = AST;
	ASTLabelType = CommonTree;
}

@header {
package net.minder;

import net.minder.X;
}

@lexer::header {
package net.minder;
}

acuml
	:	seq EOF! // ! Doesn't put into the tree.
	;

seq
	:	( 'sequence'^ | 'seq'^ ) Name code // ^ make it a root.
  ;

code
	:	'{'! stmt* '}'!
	;

stmt    
	:	( note | life | call | box )
	;

note
	:	( align | over )? Note
	;

life
	:	align? Name ( '=' type )? ( '('! input ')'! )? ( ';'! | code )
	;

type
	:	Name
	;

call
  	:	align? ( '['! guard ']'! )? source? arrow? target '.' method ( '('! input ')'! )? ( ':'! output )? ( code | ';'! )
  	;
//call [ Call c ]
//  	:	align? ( '[' guard ']' )? source? arrow? target '.' method ( '(' input ')' )? ( ':' output )? ( code | ';' ) { $c = new Call(); }
//  	;

guard
	:	list
	;

source
	:	Name
	;

arrow
	:	'>'
	;

target
	:	Name
	;

method
	:	Name
	;

input
	:	list
	;

output
	:	list
	;

list
	:	expr ( ','! expr )*
	;

expr
	:	Name ( '='! Name )*
	;

box
	:	'box'^ Name ( '['! guard ']'! )? code
	;

align
	:	left | right | center
	;

left
	:	'@Left'
	;

right
	:	'@Right'
	;

center
	:	'@Center'
	;

over
	:	'@Over(' Name ')'
	;

//foreground
//	:	'@' ( 'Color' | 'Foreground' ) '(' Name ')'
//	;

//background
//	:	'@Background(' Name ')'
//	;

//actor
//	:	'@Actor'
//	;

//label
//	:	'@' ( 'Name' | 'Label' '(' Name ')'
//	;

//wrap
//	:	'@Wrap(' Integer ')'
//	;

//autonumber
//	:	'@AutoNumber' ( '(' Integer ( ',' Integer )? ')' )?
//	;

BlockComment
	:	'/*' .* '*/' {$channel=HIDDEN;}
	;

LineComment
	:	'//' .* ( '\n' | '\r' ) {$channel=HIDDEN;}
	;

Note
	:	'"' .* '"'
	;

Name
	:	( 'a'..'z' | 'A'..'Z' | '_' ) ( 'a'..'z' | 'A'..'Z' | '0'..'9' | '_' )*
	|	'\'' .* '\''
	;

Integer
	:	( '0'..'9' ) ( '.' ( '0'..'9' )+ )?
	;

WS	:	( ' ' | '\t' | '\n' | '\r' ) {$channel=HIDDEN;}
	;
