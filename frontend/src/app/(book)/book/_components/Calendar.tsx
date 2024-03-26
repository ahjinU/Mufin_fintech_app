'use client';

import React, { useEffect } from 'react';
import { addDays, addMonths, format, subMonths } from 'date-fns';
import useDate from '@/utils/date';
import DateList from './DateList';
import DayList from './DayList';
import { ChevronRightIcon, ChevronLeftIcon } from '@heroicons/react/24/solid';

export default function Calendar() {
  const { currentMonth, setCurrentMonth, calculateDateRange } = useDate();
  const { startDate, endDate } = calculateDateRange();

  const days = [];
  let day = startDate;

  while (day < endDate) {
    days.push(day);
    day = addDays(day, 1);
  }

  const movePrevMonth = () => {
    setCurrentMonth(subMonths(currentMonth, 1));
  };
  const moveNextMonth = () => {
    setCurrentMonth(addMonths(currentMonth, 1));
  };

  return (
    <div className="flex flex-col items-center w-full">
      <div className="flex w-full justify-around h-[2rem]">
        <ChevronLeftIcon
          onClick={movePrevMonth}
          className="text-custom-medium-gray cursor-pointer"
        />
        <p className="custom-semibold-text text-custom-purple w-[30rem] flex justify-center items-center">
          {format(currentMonth, 'yyyy')}년 {format(currentMonth, 'M')}월
        </p>
        <ChevronRightIcon
          onClick={moveNextMonth}
          className="text-custom-medium-gray cursor-pointer"
        />
      </div>
      <DateList />
      <DayList list={days} />
    </div>
  );
}
