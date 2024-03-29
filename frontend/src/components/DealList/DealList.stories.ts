import type { Meta, StoryObj } from '@storybook/react';

import DealList from './DealList';

const meta = {
  title: 'Common/DealList',
  component: DealList,
  parameters: {},
  tags: ['autodocs'],
  argTypes: {
    mode: {
      description: 'MODIFY 수정 모드와 READONLY 모드가 있습니다.',
    },
    deals: {
      description: '영수증 내역 list입니다.',
    },
  },
} satisfies Meta<typeof DealList>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Main: Story = {
  args: {
    deals: [{ item: '하이', cnt: 3, price: 3, total: 3 }],
    mode: 'MODIFY',
  },
};
