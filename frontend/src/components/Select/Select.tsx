'use client';

import { useState } from 'react';
import { motion, AnimatePresence } from 'framer-motion';
import { ChevronDownIcon } from '@heroicons/react/24/solid';

interface SelectProps {
  mode: string;
  min: number;
  max: number;
  initialValue?: number;
  setValue: (value: number) => void;
}

export default function Select({
  mode,
  min,
  max,
  initialValue,
  setValue,
}: SelectProps) {
  const [isOpen, setIsOpen] = useState<boolean>(false);
  const [selected, setSelected] = useState<number>(initialValue || 1);

  const isDisabled: boolean = mode === 'LOAN_REPORT';

  let unitString: string;
  switch (mode) {
    case 'LOAN': {
      unitString = '일';
      break;
    }
    case 'LOAN_RETURN':
    case 'SAVINGS_RETURN': {
      unitString = '달 치';
      break;
    }
    case 'LOAN_REPORT': {
      unitString = '일';
      break;
    }
    case 'SAVINGS': {
      unitString = '개월';
      break;
    }
    default:
      unitString = '';
  }

  const numberArr: number[] = Array.from(
    { length: max - min + 1 },
    (_, i) => min + i,
  );

  const container = {
    show: { y: 0, opacity: 1 },
    hidden: { y: -20, opacity: 0 },
  };

  return (
    <section>
      <div className="flex gap-[0.7rem] items-center">
        <button
          onClick={() => !isDisabled && setIsOpen((prev) => !prev)}
          className={`relative w-[10rem] h-[4.4rem] custom-semibold-text bg-custom-white ${
            isOpen ? 'border-custom-purple' : 'border-custom-medium-gray'
          } border-[0.1rem] rounded-[0.8rem] p-[1.5rem] focus:outline-none flex items-center ${
            isOpen ? 'text-custom-black' : 'text-custom-medium-gray'
          }`}
        >
          {selected}
          <ChevronDownIcon className="h-[1.6rem] w-[1.6rem] absolute right-[0.5rem]" />
        </button>
        <span
          className={`custom-semibold-text ${
            isDisabled ? 'text-custom-medium-gray' : 'text-custom-light-purple'
          }`}
        >
          {unitString}
        </span>
      </div>
      <AnimatePresence>
        {isOpen && (
          <motion.ul
            variants={container}
            initial="hidden"
            animate={isOpen ? 'show' : 'hidden'}
            exit={{ y: -5, opacity: 0 }}
            transition={{
              type: 'spring',
              mass: 0.5,
              damping: 40,
              stiffness: 400,
            }}
            className="absolute w-[10rem] h-[10rem] overflow-y-scroll scrollbar-hide mt-[0.5rem] flex flex-col g-[1rem] bg-custom-white border-custom-medium-gray border-[0.1rem] rounded-[0.8rem] p-[0.5rem] focus:text-custom-black"
          >
            {numberArr.map((number, index) => {
              return (
                <li
                  onClick={() => {
                    setValue(number);
                    setSelected(number);
                    setIsOpen(false);
                  }}
                  key={`option-${index}`}
                  className="p-[0.5rem] pl-[1rem] rounded-[0.8rem] 
                  custom-light-text hover:bg-custom-light-purple"
                >
                  {number}
                </li>
              );
            })}
          </motion.ul>
        )}
      </AnimatePresence>
    </section>
  );
}
