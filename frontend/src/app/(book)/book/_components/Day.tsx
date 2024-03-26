'use client';

import React from 'react';
import { format, getMonth } from 'date-fns';
import Link from 'next/link';
import useBookStore from '../_store';

type DayProps = {
  children?: React.ReactNode;
  index?: number;
  day?: Date;
};

const Day = (props: DayProps) => {
  const { day } = props;
  const { updateSelectDate } = useBookStore();

  const koreanDate =
    day && encodeURIComponent(`${format(day, 'yyyy년 M월 d일')}`);

  return (
    <div
      className={`flex flex-col w-full
      text-[1.5rem] items-center text-custom-medium-gray
      `}
    >
      <div className="flex w-full flex-col leading-[1rem] items-center">
        <p className="text-[2rem]">.</p>
      </div>
      <Link
        className="cursor-pointer"
        onClick={() => {
          day && updateSelectDate(day);
        }}
        href={`/book/${koreanDate}`}
      >
        {day && format(day, 'd')}
      </Link>
      <div className="flex w-full flex-col leading-[0.8rem] mt-[-0.3rem] items-center">
        <p className="text-[0.7rem]">-20,000</p>
        <p className="text-[0.7rem]">+30,000</p>
      </div>
    </div>
  );
};

export default Day;
