/**
   @version 1.10 1999-11-13
   @author Cay Horstmann
*/

#include "Employee.h"

#include <stdio.h>

JNIEXPORT void JNICALL Java_Employee_raiseSalary(JNIEnv* env, jobject this_obj, jdouble byPercent)
{  
   /* ustala klasę */
   jclass class_Employee = (*env)->GetObjectClass(env, this_obj);

   /* pobiera identyfikator pola */
   jfieldID id_salary = (*env)->GetFieldID(env, class_Employee, "salary", "D");

   /* pobiera wartość pola */
   jdouble salary = (*env)->GetDoubleField(env, this_obj, id_salary);

   salary *= 1 + byPercent / 100;

   /* nadaje wartość polu */
   (*env)->SetDoubleField(env, this_obj, id_salary, salary);
}
