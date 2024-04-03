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
      <div className="flex w-full flex-col leading-[1.2rem] items-center h-[0.9rem] justify-center min-h-[1rem]">
        {(savingsDay || loanPaymentDay) && (
          <div className="flex flex-row mb-[-1.5rem] gap-[0.15rem] pr-[0.1rem]">
            {loanPaymentDay === true && (
              <p className="text-[0.4rem]  text-[green]">●</p>
            )}
            {savingsDay === true && (
              <p className="text-[0.4rem] text-[#f0cc5f]">●</p>
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
        <p className="text-[1.7rem]">{day && format(day, 'd')}</p>
      </Link>
      <div className="flex w-full flex-col leading-[0.8rem] mt-[-0.3rem] items-center justify-center min-h-[1.5rem]">
        <p className="text-[0.78rem] text-custom-blue font-[400] opacity-70">
          {incomeDay != '0' && incomeDay}
        </p>
        <p className="text-[0.78rem] text-custom-red font-[400] opacity-60">
          {outcomeDay != '0' && outcomeDay}
        </p>
      </div>
    </div>
  );
};

export default Day;
