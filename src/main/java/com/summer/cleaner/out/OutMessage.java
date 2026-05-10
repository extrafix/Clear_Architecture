package com.summer.cleaner.out;
import com.summer.cleaner.dto.*;
import java.util.Optional;


public class OutMessage{

private final String text;

private final Optional<ValidationMessage> validationMessageOptional;

public OutMessage(String text){
  this.text=text;
  this.validationMessageOptional = Optional.empty();
}


public OutMessage(String text,
                                   ValidationMessage validationMessage){
  this.text=text;
  this.validationMessageOptional = Optional.of(validationMessageOptional);
}

public String text(){
  return text;
}

public Optional<ValidationMessage> validationMessageOptional(){
  return validationMessageOptional;
}
}
