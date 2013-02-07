package javaxml3;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlEnum(String.class)
public enum Card { CLUBS, DIAMONDS, HEARTS, SPADES }
