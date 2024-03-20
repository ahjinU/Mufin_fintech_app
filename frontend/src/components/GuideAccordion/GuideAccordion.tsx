'use client';

import { useState } from 'react';
import Image from 'next/image';
import { ChevronDownIcon, ChevronUpIcon } from '@heroicons/react/24/solid';

interface GuideAccordionProps {
  icon: string;
  title: string;
  children?: React.ReactElement;
}

export default function GuideAccordion({
  icon,
  title,
  children,
}: GuideAccordionProps) {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <div>
      <button
        onClick={() => setIsOpen(!isOpen)}
        className={`w-full h-[5.8rem] px-[1.5rem] flex justify-between items-center bg-custom-purple ${
          isOpen ? 'rounded-t-[2rem]' : 'rounded-[2rem]'
        }`}
      >
        <div className="flex items-center gap-[1rem]">
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
        <div className="mt-[-0.1rem] pb-[2rem] px-[2rem] rounded-b-[2rem] bg-custom-purple h-fit custom-light-text text-custom-white">
          {children}
        </div>
      </div>
    </div>
  );
}
