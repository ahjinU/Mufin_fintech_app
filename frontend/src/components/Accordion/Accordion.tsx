'use client';

import { useState } from 'react';
import { ChevronDownIcon, ChevronUpIcon } from '@heroicons/react/24/solid';

interface GuideAccordionProps {
  mode: 'NORMAL' | 'EXCEPTIONAL' | 'COMPLETED';
  name: string;
  title: string;
  contents: string[][];
}

export default function GuideAccordionContent({
  mode,
  name,
  title,
  contents,
}: GuideAccordionProps) {
  const [isOpen, setIsOpen] = useState(false);
  let iconColor: string;

  switch (mode) {
    case 'NORMAL':
      iconColor = 'text-custom-blue';
      break;
    case 'EXCEPTIONAL':
      iconColor = 'text-custom-red';
      break;
    case 'COMPLETED':
      iconColor = 'text-custom-purple';
      break;
    default:
      iconColor = 'text-custom-blue';
  }

  return (
    <div>
      <button
        onClick={() => setIsOpen(!isOpen)}
        className={`w-full h-[5.0rem] px-[2rem] flex justify-between items-center bg-custom-light-gray ${
          isOpen ? 'rounded-t-[2rem]' : 'rounded-[2rem]'
        }`}
      >
        <div className="flex items-center gap-4">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="currentColor"
            className={`size-[0.8rem] ${iconColor}`}
            viewBox="0 0 16 16"
          >
            <circle cx="8" cy="8" r="8" />
          </svg>
          <div className="text-custom-black">
            <span className="custom-semibold-text">{name}</span>
            <span className="custom-medium-text">{title}</span>
          </div>
        </div>
        {isOpen ? (
          <ChevronUpIcon className="size-[2rem] fill-custom-medium-gray" />
        ) : (
          <ChevronDownIcon className="size-[2rem] fill-custom-medium-gray" />
        )}
      </button>
      <div className={`w-full ${isOpen ? 'visible' : 'hidden'}`}>
        <div className="pb-[2rem] px-[2rem] rounded-b-[2rem] bg-custom-light-gray h-fit">
          {contents.map((v, k) => {
            return (
              <div
                key={k}
                className="flex justify-between custom-medium-text text-custom-black"
              >
                <p>{v[0]}</p>
                <p>{v[1]}</p>
              </div>
            );
          })}
        </div>
      </div>
    </div>
  );
}
