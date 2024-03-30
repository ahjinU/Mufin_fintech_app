'use client';

import Button from '../Button/Button';
import { useState } from 'react';
import { motion } from 'framer-motion';
import {
  XMarkIcon,
  ExclamationTriangleIcon,
  CheckIcon,
} from '@heroicons/react/24/solid';

interface AgreeBottomSheetProps {
  title: string;
  notice: string;
  conditions: string[];
  isOpen: boolean;
  handleClickCloseButton: () => void;
  handleClickConfirmButton: () => void;
}

export default function AgreeBottomSheet({
  title,
  notice,
  conditions,
  isOpen,
  handleClickCloseButton,
  handleClickConfirmButton,
  ...props
}: AgreeBottomSheetProps) {
  const container = {
    show: { y: 0, opacity: 1 },
    hidden: { y: '100%', opacity: 0 },
  };

  const [isCheckedArray, setIsCheckedArray] = useState<boolean[]>(
    Array.from({ length: conditions.length }, () => false),
  );

  const checkIsAllTrue = (arr: boolean[]) => {
    let isAllTrue: boolean = true;
    for (const element of arr) {
      if (!element) {
        isAllTrue = false;
        break;
      }
    }
    return isAllTrue;
  };

  return (
    <motion.section
      variants={container}
      initial="hidden"
      animate={isOpen ? 'show' : 'hidden'}
      transition={{
        type: 'spring',
        mass: 0.5,
        damping: 40,
        stiffness: 400,
      }}
      className={`fixed bottom-0 left-0 right-0 w-full min-w-[36rem] min-h-[30rem] rounded-t-[1.6rem] bg-custom-white text-custom-black p-[3rem] flex flex-col justify-between`}
      {...props}
    >
      <XMarkIcon
        className="w-[2.4rem] h-[2.4rem] absolute top-[2rem] right-[2rem] cursor-pointer"
        onClick={handleClickCloseButton}
      />

      <section className="flex flex-col gap-[0.5rem]">
        <h1 className="custom-bold-text">{title}</h1>
        <p className="flex gap-[0.5rem]">
          <ExclamationTriangleIcon className="w-[2rem] h-[2rem] text-custom-light-purple" />
          <span className="custom-light-text text-custom-light-purple">
            {notice}
          </span>
        </p>
      </section>

      <section className="flex flex-col gap-[1rem]">
        {conditions.map((condition: string, index: number) => {
          return (
            <p
              className="w-fit flex gap-[0.5rem] cursor-pointer"
              key={`condition-${index}`}
              onClick={() => {
                const copyArray: boolean[] = [...isCheckedArray];
                copyArray[index] = !copyArray[index];
                setIsCheckedArray(copyArray);
              }}
            >
              <span
                className={`custom-medium-text ${
                  isCheckedArray[index]
                    ? 'text-custom-black'
                    : 'text-custom-medium-gray'
                }`}
              >
                {condition}
              </span>
              <CheckIcon
                className={`w-[2rem] h-[2rem] ${
                  isCheckedArray[index]
                    ? 'text-custom-purple'
                    : 'text-custom-medium-gray'
                }`}
              />
            </p>
          );
        })}
      </section>

      <Button
        mode={checkIsAllTrue(isCheckedArray) ? 'ACTIVE' : 'NON_ACTIVE'}
        label="확인"
        onClick={handleClickConfirmButton}
      />
    </motion.section>
  );
}
