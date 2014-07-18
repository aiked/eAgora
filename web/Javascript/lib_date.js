var months = new Array ("January","February",
                        "March","April","May",
			"June","July","August",
			"September","October",
			"November","December");
                        
function display_date()
{
    
    var new_option;
    var day_f = document.getElementById("birthDay");
    var month_f = document.getElementById("birthMonth");
    var year_f = document.getElementById("birthYear");
    
    for (var i=0;i<31;i++)
        {
            day_f.options[i] = new Option(i+1,i+1);
        }
    for (var m=0;m<12;m++)
        {
            month_f.options[m] = new Option(months[m],m+1);
        }
        
    var today = new Date();
    var cur_year = today.getFullYear()
    for (var y=0;y<100;y++)
        {
            year_f.options[y] = new Option(cur_year,cur_year);
            cur_year -= 1;
        }
}

function displayDate_EditProfile(day,month,year)
{
    //alert("Day:"+day+"  Month:"+month+"  Year:"+year);
    var new_option;
    var day_f = document.getElementById("birthDay");
    var month_f = document.getElementById("birthMonth");
    var year_f = document.getElementById("birthYear");
    for (var i=0;i<31;i++)
    {
        if((i+1)==day)
            day_f.options[i] = new Option(i+1,i+1,true,true);
        else
            day_f.options[i] = new Option(i+1,i+1,false,false);
    }
    for (var m=0;m<12;m++)
    {
        if((m+1)==month)
            month_f.options[m] = new Option(months[m],m+1,true,true);
        else
            month_f.options[m] = new Option(months[m],m+1,false,false);
    }
    var today = new Date();
    var cur_year = today.getFullYear();
    for (var y=0;y<100;y++)
    {
        if(cur_year==year)
            {year_f.options[y] = new Option(cur_year,cur_year,true,true);}
        else
            {year_f.options[y] = new Option(cur_year,cur_year,false,false);}
        cur_year -= 1;
    }
}
