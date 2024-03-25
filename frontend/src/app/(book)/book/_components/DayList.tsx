import React from 'react';
import Day from './Day';

const DayList = ({ list }: { list: Date[] }) => {
  const cal: Date[][] = [];

  for (let i = 0; i < list.length; i++) {
    i % 7 === 0 && cal.push([]);
    cal[cal.length - 1].push(list[i]);
  }

  return (
    <div className="px-[1.2rem] flex flex-col w-full items-center">
      {cal?.map((day, index) => (
        <div className="flex flex-row w-full h-[4.5rem]" key={`cal-${index}`}>
          {day?.map((day, index) => (
            <Day day={day} index={index} key={`day-${index}`} />
          ))}
        </div>
      ))}
    </div>
  );
};

export default DayList;
