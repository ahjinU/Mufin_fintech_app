'use client';

import { useState } from 'react';
import Image from 'next/image';
import { ChevronDownIcon, ChevronUpIcon } from '@heroicons/react/24/solid';

interface GuideAccordionProps {
  icon: string;
  title: string;
  children: React.ReactElement;
}

export default function GuideAccordionContent({
  icon,
  title,
  children,
}: GuideAccordionProps) {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <>
      <button
        onClick={() => setIsOpen(!isOpen)}
        className={`w-full h-[5.8rem] px-[2rem] flex justify-between items-center bg-custom-purple ${
          isOpen ? 'rounded-t-[2rem]' : 'rounded-[2rem]'
        }`}
      >
        <div className="flex items-center">
          <Image
            src={icon}
            width={30}
            height={30}
            alt={title}
            className="size-[3rem]"
          />
          <span className="custom-semibold-text text-custom-white">
            {title}
          </span>
        </div>
        {isOpen ? (
          <ChevronUpIcon className="size-[2rem] fill-custom-medium-gray" />
        ) : (
          <ChevronDownIcon className="size-[2rem] fill-custom-medium-gray" />
        )}
      </button>
      <div className={`w-full ${isOpen ? 'visible' : 'hidden'}`}>
        <div className="pb-[2rem] px-[2rem] rounded-b-[2rem] bg-custom-purple h-fit">
          {children}
        </div>
      </div>
    </>
  );
}
