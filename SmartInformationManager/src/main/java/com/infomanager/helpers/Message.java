package com.infomanager.helpers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

	    private String content;
	    private MessageType type;

	    // Getters and setters will auto generate by lombok dependency.
	    

}
