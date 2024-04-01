'use client';

import React from 'react';
import { format } from 'date-fns';
import Link from 'next/link';
import useBookStore from '../_store';

type DayProps = {
  children?: React.ReactNode;
  index?: number;
  day?: Date;
  incomeDay?: string | null;
  outcomeDay?: string | null;
  loanPaymentDay?: boolean | null;
  savingsDay?: boolean | null;
};

const Day = (dayData: DayProps) => {
  const { day, incomeDay, outcomeDay, loanPaymentDay, savingsDay } = dayData;

  const { updateSelectDate } = useBookStore();

  const koreanDate =
    day && encodeURIComponent(`${format(day, 'yyyy년 M월 d일')}`);

  return (
    <div
      className={`flex flex-col w-full
      text-[1.5rem] items-center text-custom-medium-gray
      `}
    >
      <div className="flex w-full flex-col leading-[1rem] items-center h-[0.9rem] justify-center">
        {(savingsDay || loanPaymentDay) && (
          <div className="flex flex-row">
            {savingsDay && (
              <p className="text-[2.2rem] p-[-2rem] text-custom-blue">.</p>
            )}
            {loanPaymentDay && (
              <p className="text-[2.2rem] p-[-2rem] text-custom-red">.</p>
            )}
          </div>
        )}
      </div>
      <Link
        className="cursor-pointer"
        onClick={() => {
          day && updateSelectDate(day);
        }}
        href={`/book/${koreanDate}/list`}
      >
        {day && format(day, 'd')}
      </Link>
      <div className="flex w-full flex-col leading-[0.8rem] mt-[-0.3rem] items-center">
        <p className="text-[0.75rem] text-custom-blue font-[200]">
          {incomeDay != '0' && incomeDay}
        </p>
        <p className="text-[0.75rem] text-custom-red font-[200]">
          {outcomeDay != '0' && outcomeDay}
        </p>
      </div>
    </div>
  );
};

export default Day;
