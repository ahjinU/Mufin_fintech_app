'use client';

import Image from 'next/image';
import {
  ChevronRightIcon,
  ArrowTrendingDownIcon,
  ArrowTrendingUpIcon,
} from '@heroicons/react/24/solid';
import TinyButton from '../TinyButton/TinyButton';
import Link from 'next/link';
import { useRouter } from 'next/navigation';

interface MoneyInfoElementProps {
  imageSrc: string;
  leftExplainText: string;
  leftHighlightText: string;
  buttonOption:
    | 'RIGHT_ARROW'
    | 'TINY_BUTTON'
    | 'RATE'
    | 'STOCK_UP'
    | 'STOCK_DOWN'
    | 'NO';
  tinyButtonLabel?: string;
  stockPrice?: string;
  link?: string;
}

function RateShow() {
  return (
    <div
      className={`min-w-fit h-[1.8rem] px-[0.8rem] rounded-[0.8rem]
        text-custom-white custom-light-text bg-custom-light-purple`}
    >
      금리 연 2%
    </div>
  );
}

function StockUpShow({ stockUpPrice }: { stockUpPrice: string }) {
  return (
    <div className="min-w-fit flex gap-[0.3rem] items-center">
      <ArrowTrendingUpIcon className="w-[1.5rem] h-[1.5rem] text-custom-red" />
      <span className="custom-light-text text-custom-red">{stockUpPrice}</span>
    </div>
  );
}

function StockDownShow({ stockDownPrice }: { stockDownPrice: string }) {
  return (
    <div className="min-w-fit flex gap-[0.3rem] items-center">
      <ArrowTrendingDownIcon className="w-[1.5rem] h-[1.5rem] text-custom-blue" />
      <span className="custom-light-text text-custom-blue">
        {stockDownPrice}
      </span>
    </div>
  );
}

export default function MoneyInfoElement({
  imageSrc,
  leftExplainText,
  leftHighlightText,
  buttonOption,
  tinyButtonLabel,
  stockPrice,
  link,
}: MoneyInfoElementProps) {
  const router = useRouter();
  const RightButton: React.FC = () => {
    if (buttonOption === 'RIGHT_ARROW')
      return (
        <ChevronRightIcon className="w-[1.6rem] h-[1.6rem] text-custom-medium-gray cursor-pointer" />
      );
    if (buttonOption === 'TINY_BUTTON' && tinyButtonLabel)
      return (
        <TinyButton
          label={tinyButtonLabel}
          onClick={() => link && router.push(link)}
        />
      );
    if (buttonOption === 'RATE') return <RateShow />;
    if (buttonOption === 'STOCK_DOWN' && stockPrice)
      return <StockDownShow stockDownPrice={stockPrice} />;
    if (buttonOption === 'STOCK_UP' && stockPrice)
      return <StockUpShow stockUpPrice={stockPrice} />;
  };

  function Content() {
    return (
      <section className="w-full flex justify-between items-center">
        <div className="w-full flex gap-[1rem] items-center">
          <Image
            src={imageSrc}
            width={42}
            height={42}
            alt={leftExplainText}
            className="w-[4.2rem] h-[4.2rem]"
          />
          <div className="flex flex-col justify-between">
            <span className="custom-medium-text">{leftExplainText}</span>
            <span className="custom-semibold-text">{leftHighlightText}</span>
          </div>
        </div>
        <RightButton />
      </section>
    );
  }

  return buttonOption === 'RIGHT_ARROW' && link ? (
    <Link href={link}>
      <Content />
    </Link>
  ) : (
    <section className="w-full flex justify-between items-center">
      <Content />
    </section>
  );
}
