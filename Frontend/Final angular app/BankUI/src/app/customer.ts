export class customer{

    private custid:any;
    constructor(private _activated:boolean, 
    private firstname:string,private lastname:string,
    private aadharnumber:string,private pannumber:string,private phoneno:string,
    private dob:Date,private email:string,private username:string,private password:string,
    private address:string,private pincode:string){}
    
    
    get first_name():string{
    return this.firstname;
    }
    
    get last_name():string{
    return this.lastname;
    }
    get aadhar_number():string{
    return this.aadharnumber;
    }
    get pan_number():string{
    return this.pannumber;
    }
    get phone_no():string{
    return this.phoneno;
    }
    get dob_():Date{
    return this.dob;
    }
    get email_():string{
    return this.email;
    }
    get user_name():string{
    return this.username;
    }
    get password_():string{
    return this.password;
    }
    get address_():string{
    return this.address;
    
    }
    get pin_code():string{
    return this.pincode;
    }
    
    get cust_id():any{
    return this.custid;
    }
    }