import React from 'react';
import { format, getMonth } from 'date-fns';
import useDate from '@/utils/date';

type DayProps = {
  children?: React.ReactNode;
  index?: number;
  day?: Date;
};

const Day = (props: DayProps) => {
  const { index, day } = props;
  const { currentMonth } = useDate();
  console.log(currentMonth);

  return (
    <div
      className={`flex flex-col w-full  
      text-[1.5rem] items-center text-custom-medium-gray
      `}
    >
      <div className="flex w-full flex-col leading-[1rem] items-center">
        <p className="text-[2rem]">.</p>
      </div>
      {day && format(day, 'd')}
      <div className="flex w-full flex-col leading-[1rem] mt-[-0.3rem] items-center">
        <p className="text-[0.8rem]">-20,000</p>
        <p className="text-[0.8rem]">+30,000</p>
      </div>
    </div>
  );
};

export default Day;
