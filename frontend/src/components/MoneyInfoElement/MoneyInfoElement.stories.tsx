import type { Meta, StoryObj } from '@storybook/react';

import MoneyInfoElement from './MoneyInfoElement';

const meta = {
  title: 'Common/MoneyInfoElement',
  component: MoneyInfoElement,
  parameters: {},
  tags: ['autodocs'],
  argTypes: {},
} satisfies Meta<typeof MoneyInfoElement>;

export default meta;
type Story = StoryObj<typeof meta>;

export const RightArrow: Story = {
  args: {
    imageSrc: 'http://localhost:3000/images/icon-dollar.png',
    leftExplainText: '내 요술램프와 주식 평가 합산',
    leftHighlightText: '220,000,000 자스민',
    buttonOption: 'RIGHT_ARROW',
  },
};

export const TinyButton: Story = {
  args: {
    imageSrc: 'http://localhost:3000/images/icon-dollar.png',
    leftExplainText: '이번 달 쓴 돈',
    leftHighlightText: '83,000 원',
    buttonOption: 'TINY_BUTTON',
    tinyButtonLabel: '가계부',
  },
};

export const Rate: Story = {
  args: {
    imageSrc: 'http://localhost:3000/images/icon-dollar.png',
    leftExplainText: '내 요술램프',
    leftHighlightText: '1,724,900 자스민',
    buttonOption: 'RATE',
  },
};

export const StockUp: Story = {
  args: {
    imageSrc: 'http://localhost:3000/images/icon-dollar.png',
    leftExplainText: '내 주식 평가',
    leftHighlightText: '380,900 자스민',
    buttonOption: 'STOCK_UP',
    stockPrice: '140,000',
  },
};

export const StockDown: Story = {
  args: {
    imageSrc: 'http://localhost:3000/images/icon-dollar.png',
    leftExplainText: '내 주식 평가',
    leftHighlightText: '380,900 자스민',
    buttonOption: 'STOCK_DOWN',
    stockPrice: '140,000',
  },
};
