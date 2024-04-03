'use client';

import Image from 'next/image';
import { motion } from 'framer-motion';
import { XMarkIcon } from '@heroicons/react/24/solid';
import React, { ReactNode } from 'react';
import Lottie from 'react-lottie-player';
import { Button } from '@/components';

interface BottomSheetProps {
  size: 'SMALL' | 'MEDIUM';
  title: string;
  content?: string;
  isXButtonVisible: boolean;
  isOpen: boolean;
  children?: ReactNode;
  onClose?: () => void;
  onConfirm?: () => void;
  lottie?: any;
}

export default function BottomSheet({
  size,
  title,
  content,
  isXButtonVisible,
  isOpen,
  children,
  onConfirm,
  onClose,
  lottie,
  ...props
}: BottomSheetProps) {
  const height: string = size === 'SMALL' ? 'h-[30rem]' : 'h-[40rem]';
  const isButtonVisible: boolean = size !== 'SMALL';

  const container = {
    show: { y: 0, opacity: 1 },
    hidden: { y: '100%', opacity: 0 },
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
      className={`fixed bottom-0 left-0 right-0 w-full min-w-[36rem] ${height} rounded-t-[1.6rem] bg-custom-white text-custom-black p-[3rem] flex flex-col justify-between`}
      {...props}
    >
      {isXButtonVisible && (
        <XMarkIcon
          className="w-[2.4rem] h-[2.4rem] absolute top-[2rem] right-[2rem] cursor-pointer"
          onClick={onClose}
        />
      )}
      <h1 className="custom-bold-text">{title}</h1>
      {content && <p className="custom-semibold-text text-wrap">{content}</p>}

      <Lottie
        loop
        animationData={lottie}
        play
        style={{ width: 200, height: 200 }}
        className="self-center"
      />

      {children}
      {isButtonVisible && (
        <Button mode="ACTIVE" label="확인" onClick={onConfirm} />
      )}
    </motion.section>
  );
}
