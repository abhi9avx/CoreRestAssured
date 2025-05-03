package com.rest;

public class MethodChaining {
    public static void main(String[] args) {
        //MethodChaining methodChaining = new MethodChaining();
        a1().a2().a3();
    }

    public static MethodChaining a1() {
        System.out.println("This is a1 method");
        return new MethodChaining();
    }

    public MethodChaining a2() {
        System.out.println("This is a2 method");
        return this;
    }

    public MethodChaining a3() {
        System.out.println("This is a3 method");
        return this;
    }
}

/*

 Sure, Abhinav! Here’s a clean summary of your MethodChaining class along with a breakdown of how it works:

⸻

✅ Summary of MethodChaining Example

📌 Purpose:

This class demonstrates method chaining — a design pattern where multiple method calls are linked together in a single statement, making code more fluent and readable.

⸻

🔍 Code Flow Explained:

a1().a2().a3();

	1.	a1() is static, so it’s called without creating an object:
	•	Prints: "This is a1 method"
	•	Returns a new instance of MethodChaining
	2.	On that returned instance, a2() is called:
	•	Prints: "This is a2 method"
	•	Returns this (the current object)
	3.	Then, a3() is called on the same object:
	•	Prints: "This is a3 method"
	•	Returns this again

⸻

🧠 Concepts Demonstrated:

Concept	Explanation
Method Chaining	Calling multiple methods on the same object instance in one line
return this	Returns the current object to allow chaining
Static + Non-static Mix	a1() is static and creates the object, while a2() and a3() are instance methods



⸻

💡 Why It’s Useful:
	•	Makes code fluent and readable
	•	Common in test automation frameworks (like Rest Assured)
	•	Used in builder patterns for object creation

⸻

Let me know if you’d like to convert this into a real-world example like using RestAssured, or a UserBuilder class to practice further!

*/